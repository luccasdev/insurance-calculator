package luccas.dev.insurancecalculator.application.gateways;

import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;

import java.util.Optional;
import java.util.UUID;

public interface InsuranceProductGateway {

    InsuranceProduct create(InsuranceProduct insuranceProduct);

    InsuranceProduct update(InsuranceProduct insuranceProduct);

    Optional<InsuranceProduct> findById(UUID id);

}
