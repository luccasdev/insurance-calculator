package luccas.dev.insurancecalculator.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InsuranceProductRepository extends CrudRepository<InsuranceProductEntity, UUID> {

}
