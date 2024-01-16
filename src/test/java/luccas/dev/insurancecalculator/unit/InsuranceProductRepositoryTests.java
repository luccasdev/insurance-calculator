package luccas.dev.insurancecalculator.unit;

import luccas.dev.insurancecalculator.application.exceptions.NotFoundException;
import luccas.dev.insurancecalculator.application.gateways.InsuranceProductGateway;
import luccas.dev.insurancecalculator.application.strategies.AutoInsuranceTariffStrategy;
import luccas.dev.insurancecalculator.application.usecases.InsuranceProductInteractor;
import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;
import luccas.dev.insurancecalculator.infrastructure.gateways.InsuranceEntityMapper;
import luccas.dev.insurancecalculator.infrastructure.gateways.InsuranceRepositoryGateway;
import luccas.dev.insurancecalculator.infrastructure.persistence.InsuranceProductRepository;
import luccas.dev.insurancecalculator.util.InsuranceProductMockCreatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InsuranceProductRepositoryTests {

    @Mock
    private InsuranceProductRepository insuranceProductRepository;

    @InjectMocks
    private InsuranceRepositoryGateway insuranceRepositoryGateway;

    @Test
    void shouldSaveInsuranceProductWithSuccess() {
        var entityMock = InsuranceEntityMapper.toEntity(InsuranceProductMockCreatorUtil.createDomain());

        when(insuranceProductRepository.save(entityMock)).thenReturn(entityMock);

        var createdInsuranceProduct = insuranceRepositoryGateway.create(InsuranceEntityMapper.toDomain(entityMock));

        verify(insuranceProductRepository, atLeastOnce()).save(any());
        assertEquals(createdInsuranceProduct.getId(), entityMock.getId());
        assertEquals(createdInsuranceProduct.getName(), entityMock.getName());
    }

    @Test
    void shouldUpdateInsuranceProductWithSuccess() {
        var entityMock = InsuranceEntityMapper.toEntity(InsuranceProductMockCreatorUtil.createDomain());

        when(insuranceProductRepository.save(entityMock)).thenReturn(entityMock);

        var createdInsuranceProduct = insuranceRepositoryGateway.update(InsuranceEntityMapper.toDomain(entityMock));

        verify(insuranceProductRepository, atLeastOnce()).save(any());
        assertEquals(createdInsuranceProduct.getId(), entityMock.getId());
        assertEquals(createdInsuranceProduct.getName(), entityMock.getName());
    }

    @Test
    void shouldFindInsuranceProductWithSuccess() {
        var entityMock = InsuranceEntityMapper.toEntity(InsuranceProductMockCreatorUtil.createDomain());

        when(insuranceProductRepository.findById(entityMock.getId())).thenReturn(Optional.of(entityMock));

        var createdInsuranceProduct = insuranceRepositoryGateway.findById(entityMock.getId());

        verify(insuranceProductRepository, atLeastOnce()).findById(any());
        assertEquals(createdInsuranceProduct.get().getId(), entityMock.getId());
        assertEquals(createdInsuranceProduct.get().getName(), entityMock.getName());
    }
}
