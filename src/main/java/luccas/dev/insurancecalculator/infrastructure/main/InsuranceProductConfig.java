package luccas.dev.insurancecalculator.infrastructure.main;

import luccas.dev.insurancecalculator.application.gateways.InsuranceProductGateway;
import luccas.dev.insurancecalculator.application.usecases.InsuranceProductInteractor;
import luccas.dev.insurancecalculator.infrastructure.gateways.InsuranceRepositoryGateway;
import luccas.dev.insurancecalculator.infrastructure.persistence.InsuranceProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsuranceProductConfig {

    @Bean
    InsuranceProductInteractor createInsuranceProductUseCase(InsuranceProductGateway insuranceProductGateway) {
        return new InsuranceProductInteractor(insuranceProductGateway);
    }

    @Bean
    InsuranceProductGateway insuranceProductGateway(InsuranceProductRepository insuranceProductRepository) {
        return new InsuranceRepositoryGateway(insuranceProductRepository);
    }

}
