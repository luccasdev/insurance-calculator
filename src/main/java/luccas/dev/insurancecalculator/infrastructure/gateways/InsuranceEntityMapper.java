package luccas.dev.insurancecalculator.infrastructure.gateways;

import lombok.experimental.UtilityClass;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;
import luccas.dev.insurancecalculator.infrastructure.persistence.InsuranceProductEntity;

@UtilityClass
public class InsuranceEntityMapper {

    public static InsuranceProductEntity toEntity(InsuranceProduct insuranceProductDomain) {
        return InsuranceProductEntity.builder()
                .id(insuranceProductDomain.getId())
                .category(insuranceProductDomain.getCategory())
                .priceBase(insuranceProductDomain.getPriceBase())
                .priceWithTariff(insuranceProductDomain.getPriceWithTariff())
                .name(insuranceProductDomain.getName())
                .build();
    }

    public static InsuranceProduct toDomain(InsuranceProductEntity insuranceProductEntity) {
        return InsuranceProduct.builder()
                .id(insuranceProductEntity.getId())
                .category(insuranceProductEntity.getCategory())
                .priceBase(insuranceProductEntity.getPriceBase())
                .priceWithTariff(insuranceProductEntity.getPriceWithTariff())
                .name(insuranceProductEntity.getName())
                .build();
    }


}
