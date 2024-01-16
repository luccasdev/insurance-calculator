package luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;

import java.math.BigDecimal;

@Builder
public record UpdateInsuranceProductRequest(
        @JsonProperty("nome")
        String name,

        @JsonProperty("categoria")
        InsuranceCategory category,

        @JsonProperty("preco_base")
        BigDecimal priceBase
) {

    public static InsuranceProduct toDomain(UpdateInsuranceProductRequest insuranceProductRequest) {
        return InsuranceProduct.builder()
                .name(insuranceProductRequest.name())
                .category(insuranceProductRequest.category())
                .priceBase(insuranceProductRequest.priceBase())
                .build();
    }
}
