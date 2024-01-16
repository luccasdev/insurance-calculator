package luccas.dev.insurancecalculator.util;


import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;

import java.math.BigDecimal;
import java.util.UUID;

public class InsuranceProductMockCreatorUtil {

    public static InsuranceProduct createDomain() {
        return InsuranceProduct.builder()
                .name("Insurance Product Mock")
                .priceWithTariff(BigDecimal.valueOf(100.0))
                .priceBase(BigDecimal.valueOf(100.0))
                .category(InsuranceCategory.AUTO)
                .id(UUID.randomUUID())
                .build();
    }
}
