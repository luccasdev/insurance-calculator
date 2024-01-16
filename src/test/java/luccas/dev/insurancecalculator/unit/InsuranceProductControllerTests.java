package luccas.dev.insurancecalculator.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import luccas.dev.insurancecalculator.application.exceptions.NotFoundException;
import luccas.dev.insurancecalculator.application.usecases.InsuranceProductInteractor;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request.CreateInsuranceProductRequest;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request.UpdateInsuranceProductRequest;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response.CreateInsuranceProductResponse;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response.UpdateInsuranceProductResponse;
import luccas.dev.insurancecalculator.util.InsuranceProductMockCreatorUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InsuranceProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    InsuranceProductInteractor insuranceProductInteractor;

    @Test
    void shouldFindExistentInsuranceWithSuccess() throws Exception {
        var insuranceMock = InsuranceProductMockCreatorUtil.createDomain();

        Mockito.when(insuranceProductInteractor.findById(any())).thenReturn(insuranceMock);

        mockMvc.perform(MockMvcRequestBuilders.get("/insurances/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(insuranceMock.getName()));
    }

    @Test
    void shouldFindInsuranceWithNotFoundError() throws Exception {
        Mockito.when(insuranceProductInteractor.findById(any())).thenThrow(new NotFoundException("Não encontrado!"));

        mockMvc.perform(MockMvcRequestBuilders.get("/insurances/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldFindInsuranceWithInternalServerError() throws Exception {
        Mockito.when(insuranceProductInteractor.findById(any())).thenThrow(new InternalError());

        mockMvc.perform(MockMvcRequestBuilders.get("/insurances/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldCreateInsuranceWithSuccess() throws Exception {
        var insuranceMock = InsuranceProductMockCreatorUtil.createDomain();
        var insuranceCreateRequestMock = CreateInsuranceProductRequest
                .builder()
                .name(insuranceMock.getName())
                .category(insuranceMock.getCategory())
                .priceBase(insuranceMock.getPriceBase())
                .build();

        var insuranceCreateResponseMock = CreateInsuranceProductResponse.fromDomain(insuranceMock);

        Mockito.when(insuranceProductInteractor.createInsuranceProduct(any())).thenReturn(insuranceMock);

        mockMvc.perform(MockMvcRequestBuilders.post("/insurances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsBytes(insuranceCreateRequestMock)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(insuranceCreateResponseMock)))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(insuranceMock.getName()));
    }

    @Test
    void shouldUpdateInsuranceWithSuccess() throws Exception {
        var insuranceMock = InsuranceProductMockCreatorUtil.createDomain();
        var insuranceUpdateRequestMock = UpdateInsuranceProductRequest
                .builder()
                .name(insuranceMock.getName())
                .category(insuranceMock.getCategory())
                .priceBase(insuranceMock.getPriceBase())
                .build();

        var insuranceUpdateResponseMock = UpdateInsuranceProductResponse.fromDomain(insuranceMock);

        Mockito.when(insuranceProductInteractor.partialUpdateInsuranceProduct(any(), any())).thenReturn(insuranceMock);

        mockMvc.perform(MockMvcRequestBuilders.patch("/insurances/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsBytes(insuranceUpdateRequestMock)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(this.mapper.writeValueAsString(insuranceUpdateResponseMock)))
                .andExpect(MockMvcResultMatchers.jsonPath("nome").value(insuranceMock.getName()));
    }

}
