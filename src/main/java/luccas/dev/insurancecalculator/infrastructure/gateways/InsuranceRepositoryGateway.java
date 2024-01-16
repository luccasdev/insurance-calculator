package luccas.dev.insurancecalculator.infrastructure.gateways;

import lombok.RequiredArgsConstructor;
import luccas.dev.insurancecalculator.application.gateways.InsuranceProductGateway;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;
import luccas.dev.insurancecalculator.infrastructure.persistence.InsuranceProductRepository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class InsuranceRepositoryGateway implements InsuranceProductGateway {

    private final InsuranceProductRepository insuranceProductRepository;

    public InsuranceProduct create(InsuranceProduct insuranceProduct) {
        return InsuranceEntityMapper.toDomain(
                insuranceProductRepository.save(InsuranceEntityMapper.toEntity(insuranceProduct)));
    }

    public InsuranceProduct update(InsuranceProduct insuranceProduct) {
        return InsuranceEntityMapper.toDomain(
                insuranceProductRepository.save(InsuranceEntityMapper.toEntity(insuranceProduct)));
    }

    public Optional<InsuranceProduct> findById(UUID id) {
        return insuranceProductRepository.findById(id).map(InsuranceEntityMapper::toDomain);
    }

}
