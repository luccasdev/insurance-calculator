package luccas.dev.insurancecalculator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import luccas.dev.insurancecalculator.application.strategies.*;

@Getter
@AllArgsConstructor
public enum InsuranceCategory {
    VIDA(new HealthInsuranceTariffStrategy()),
    AUTO(new AutoInsuranceTariffStrategy()),
    VIAGEM(new TravelInsuranceTariffStrategy()),
    RESIDENCIAL(new HomeInsuranceTariffStrategy()),
    PATRIMONIAL(new PatrimonialInsuranceTariffStrategy());

    private final InsuranceTariffStrategy strategy;

}
