package luccas.dev.insurancecalculator.application.strategies;

import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
public class PatrimonialInsuranceTariffStrategy implements InsuranceTariffStrategy {

    private final static Double IOF_TARIFF = 0.05;
    private final static Double PIS_TARIFF = 0.03;

    public BigDecimal calculate(BigDecimal priceBase) {
        return priceBase.add(
                (priceBase.multiply(BigDecimal.valueOf(IOF_TARIFF)))
                                .add((priceBase.multiply(BigDecimal.valueOf(PIS_TARIFF)))));
    }

}
