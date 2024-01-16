package luccas.dev.insurancecalculator.application.usecases;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import luccas.dev.insurancecalculator.application.exceptions.NotFoundException;
import luccas.dev.insurancecalculator.application.gateways.InsuranceProductGateway;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class InsuranceProductInteractor {

    private final InsuranceProductGateway insuranceProductGateway;

    public InsuranceProduct createInsuranceProduct(InsuranceProduct insuranceProduct) {
        log.info("Creating insurance product with category: {}", insuranceProduct.getCategory());

        insuranceProduct.setPriceWithTariff(
                insuranceProduct.getCategory().getStrategy()
                        .calculate(insuranceProduct.getPriceBase()));
        return insuranceProductGateway.create(insuranceProduct);
    }

    public InsuranceProduct partialUpdateInsuranceProduct(UUID id, InsuranceProduct insuranceProduct) {
        var existentInsuranceProduct = this.findById(id);

        log.info("Updating insurance product with id: {}", insuranceProduct.getId());

        Optional.ofNullable(insuranceProduct.getCategory())
                .ifPresent(existentInsuranceProduct::setCategory);

        Optional.ofNullable(insuranceProduct.getPriceBase())
                .ifPresent(existentInsuranceProduct::setPriceBase);

        Optional.ofNullable(insuranceProduct.getName())
                .ifPresent(existentInsuranceProduct::setName);

        existentInsuranceProduct.setPriceWithTariff(
                existentInsuranceProduct.getCategory().getStrategy().calculate(existentInsuranceProduct.getPriceBase()));

        return insuranceProductGateway.update(existentInsuranceProduct);
    }

    public InsuranceProduct findById(UUID id) {
        log.info("Finding insurance product with id: {}", id);

        return insuranceProductGateway.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto com ID: " + id.toString() + " não encontrado."));
    }

}
