package luccas.dev.insurancecalculator.application.strategies;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class HealthInsuranceTariffStrategy implements InsuranceTariffStrategy {

    private final static Double IOF_TARIFF = 0.01;
    private final static Double PIS_TARIFF = 0.022;

    public BigDecimal calculate(BigDecimal priceBase) {
        return priceBase.add(
                (priceBase.multiply(BigDecimal.valueOf(IOF_TARIFF))).add(
                        (priceBase.multiply(BigDecimal.valueOf(PIS_TARIFF)))));
    }

}
