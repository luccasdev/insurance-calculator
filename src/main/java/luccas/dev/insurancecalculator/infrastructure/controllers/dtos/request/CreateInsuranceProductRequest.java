package luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Generated;
import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;

import java.math.BigDecimal;

@Builder
public record CreateInsuranceProductRequest(
        @NotBlank
        @JsonProperty("nome")
        String name,

        @NotNull
        @JsonProperty("categoria")
        InsuranceCategory category,

        @NotNull
        @JsonProperty("preco_base")
        BigDecimal priceBase
) {

    public static InsuranceProduct toDomain(CreateInsuranceProductRequest insuranceProductRequest) {
        return InsuranceProduct.builder()
                .name(insuranceProductRequest.name())
                .category(insuranceProductRequest.category())
                .priceBase(insuranceProductRequest.priceBase())
                .build();
    }
}
