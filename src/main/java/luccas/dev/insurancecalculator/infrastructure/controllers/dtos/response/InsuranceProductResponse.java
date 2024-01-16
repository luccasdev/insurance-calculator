package luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Generated;
import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

@Builder
public record InsuranceProductResponse(
        UUID id,

        @JsonProperty("nome")
        String name,

        @JsonProperty("categoria")
        InsuranceCategory category,

        @JsonProperty("preco_base")
        BigDecimal priceBase,

        @JsonProperty("preco_tarifado")
        BigDecimal priceWithTariff
) {

    public static InsuranceProductResponse fromDomain(InsuranceProduct insuranceProduct) {
        return InsuranceProductResponse.builder()
                .id(insuranceProduct.getId())
                .name(insuranceProduct.getName())
                .category(insuranceProduct.getCategory())
                .priceBase(insuranceProduct.getPriceBase().setScale(2, RoundingMode.HALF_UP))
                .priceWithTariff(insuranceProduct.getPriceWithTariff().setScale(2, RoundingMode.HALF_UP))
                .build();
    }

}