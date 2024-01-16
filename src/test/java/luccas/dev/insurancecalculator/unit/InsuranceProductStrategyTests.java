package luccas.dev.insurancecalculator.unit;

import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class InsuranceProductStrategyTests {

    @Test
    void shouldCalculateTravelInsuranceTariffStrategy() {
        var strategyCategory = InsuranceCategory.VIAGEM;
        var priceBase = BigDecimal.valueOf(150);

        var priceWithTariff = strategyCategory.getStrategy().calculate(priceBase);

        assertEquals(priceWithTariff.setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(160.50).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateHomeInsuranceTariffStrategy() {
        var strategyCategory = InsuranceCategory.RESIDENCIAL;
        var priceBase = BigDecimal.valueOf(150);

        var priceWithTariff = strategyCategory.getStrategy().calculate(priceBase);

        assertEquals(priceWithTariff.setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(160.50).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculateAutoInsuranceTariffStrategy() {
        var strategyCategory = InsuranceCategory.AUTO;
        var priceBase = BigDecimal.valueOf(150);

        var priceWithTariff = strategyCategory.getStrategy().calculate(priceBase);

        assertEquals(priceWithTariff.setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(165.75).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void shouldCalculatePatrimonialInsuranceTariffStrategy() {
        var strategyCategory = InsuranceCategory.PATRIMONIAL;
        var priceBase = BigDecimal.valueOf(150);

        var priceWithTariff = strategyCategory.getStrategy().calculate(priceBase);

        assertEquals(priceWithTariff.setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(162).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void shouldHealthInsuranceTariffStrategy() {
        var strategyCategory = InsuranceCategory.VIDA;
        var priceBase = BigDecimal.valueOf(150);

        var priceWithTariff = strategyCategory.getStrategy().calculate(priceBase);

        assertEquals(priceWithTariff.setScale(2, RoundingMode.HALF_UP), BigDecimal.valueOf(154.8).setScale(2, RoundingMode.HALF_UP));
    }
}
