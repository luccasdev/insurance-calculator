package luccas.dev.insurancecalculator.application.strategies;

import java.math.BigDecimal;

public interface InsuranceTariffStrategy {


    BigDecimal calculate(BigDecimal priceBase);

}
