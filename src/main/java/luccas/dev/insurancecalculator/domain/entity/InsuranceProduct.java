package luccas.dev.insurancecalculator.domain.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Setter
@Getter
public class InsuranceProduct {

    private UUID id;
    private String name;
    private InsuranceCategory category;
    private BigDecimal priceBase;
    private BigDecimal priceWithTariff;

}
