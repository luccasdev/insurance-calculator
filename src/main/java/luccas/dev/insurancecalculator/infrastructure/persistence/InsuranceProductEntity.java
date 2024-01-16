package luccas.dev.insurancecalculator.infrastructure.persistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "insurance_product")
public class InsuranceProductEntity {


    @Id
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private InsuranceCategory category;

    @Column(name = "price_base", nullable = false)
    private BigDecimal priceBase;

    @Column(name = "price_with_tariff", nullable = false)
    private BigDecimal priceWithTariff;

}
