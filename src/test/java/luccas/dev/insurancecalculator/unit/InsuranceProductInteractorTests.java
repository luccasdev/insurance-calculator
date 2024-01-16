package luccas.dev.insurancecalculator.unit;

import luccas.dev.insurancecalculator.application.exceptions.NotFoundException;
import luccas.dev.insurancecalculator.application.gateways.InsuranceProductGateway;
import luccas.dev.insurancecalculator.application.strategies.AutoInsuranceTariffStrategy;
import luccas.dev.insurancecalculator.application.usecases.InsuranceProductInteractor;
import luccas.dev.insurancecalculator.domain.entity.InsuranceCategory;
import luccas.dev.insurancecalculator.domain.entity.InsuranceProduct;
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
public class InsuranceProductInteractorTests {

    @Mock
    private InsuranceProductGateway insuranceProductGateway;

    @InjectMocks
    private InsuranceProductInteractor insuranceProductInteractor;

    @Test
    void shouldCreateInsuranceProductAndCalculatePriceWithSuccess() {
        var insuranceProductMock = Mockito.mock(InsuranceProduct.class);
        var strategyMock = Mockito.mock(AutoInsuranceTariffStrategy.class);
        var categoryMock = Mockito.mock(InsuranceCategory.class);

        when(categoryMock.getStrategy()).thenReturn(strategyMock);
        when(insuranceProductMock.getCategory()).thenReturn(categoryMock);

        when(insuranceProductGateway.create(any())).thenReturn(insuranceProductMock);

        var createdInsuranceProduct = insuranceProductInteractor.createInsuranceProduct(insuranceProductMock);

        verify(insuranceProductGateway, atLeastOnce()).create(any());
        verify(insuranceProductMock.getCategory().getStrategy(), atLeastOnce()).calculate(any());
        assertEquals(createdInsuranceProduct.getId(), insuranceProductMock.getId());
        assertEquals(createdInsuranceProduct.getName(), insuranceProductMock.getName());
    }


    @Test
    void shouldUpdateInsuranceProductAndCalculateNewPriceWithSuccess() {
        var insuranceProductMock = Mockito.mock(InsuranceProduct.class);
        var strategyMock = Mockito.mock(AutoInsuranceTariffStrategy.class);
        var categoryMock = Mockito.mock(InsuranceCategory.class);

        when(categoryMock.getStrategy()).thenReturn(strategyMock);
        when(insuranceProductMock.getCategory()).thenReturn(categoryMock);
        when(insuranceProductMock.getName()).thenReturn("Updated Name");

        when(insuranceProductGateway.findById(any())).thenReturn(Optional.of(InsuranceProductMockCreatorUtil.createDomain()));
        when(insuranceProductGateway.update(any())).thenReturn(insuranceProductMock);

        var updatedInsuranceProduct = insuranceProductInteractor.partialUpdateInsuranceProduct(insuranceProductMock.getId(), insuranceProductMock);

        verify(insuranceProductGateway, atLeastOnce()).update(any());
        verify(insuranceProductMock.getCategory().getStrategy(), atLeastOnce()).calculate(any());
        assertEquals(updatedInsuranceProduct.getName(), insuranceProductMock.getName());
    }

    @Test
    void shouldUpdateInsuranceProductByIdWithNotFoundError() {
        var insuranceProductMock = InsuranceProductMockCreatorUtil.createDomain();

        when(insuranceProductGateway.findById(any())).thenThrow(new NotFoundException("Não encontrado"));

        assertThrows(NotFoundException.class, () -> insuranceProductInteractor.partialUpdateInsuranceProduct(insuranceProductMock.getId(), insuranceProductMock));
    }

    @Test
    void shouldFindInsuranceProductByIdWithSuccess() {
        var insuranceProductMock = InsuranceProductMockCreatorUtil.createDomain();

        when(insuranceProductGateway.findById(any())).thenReturn(Optional.of(insuranceProductMock));

        var foundedInsurance = insuranceProductInteractor.findById(insuranceProductMock.getId());

        verify(insuranceProductGateway, atLeastOnce()).findById(any());
        assertEquals(foundedInsurance.getName(), insuranceProductMock.getName());
    }

    @Test
    void shouldFindInsuranceProductByIdWithNotFoundError() {
        assertThrows(NotFoundException.class, () -> insuranceProductInteractor.findById(UUID.randomUUID()));
    }

}
