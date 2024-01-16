package luccas.dev.insurancecalculator.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request.CreateInsuranceProductRequest;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response.InsuranceProductResponse;
import luccas.dev.insurancecalculator.infrastructure.gateways.InsuranceEntityMapper;
import luccas.dev.insurancecalculator.infrastructure.persistence.InsuranceProductRepository;
import luccas.dev.insurancecalculator.util.InsuranceProductMockCreatorUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InsuranceProductControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private InsuranceProductRepository insuranceProductRepository;

    @Test
    void shouldCreateInsuranceWithSuccess() throws Exception {
        var insuranceMock = InsuranceProductMockCreatorUtil.createDomain();
        var insuranceCreateRequestMock = CreateInsuranceProductRequest
                .builder()
                .name(insuranceMock.getName())
                .category(insuranceMock.getCategory())
                .priceBase(insuranceMock.getPriceBase())
                .build();


        var responseAsString = mockMvc.perform(MockMvcRequestBuilders.post("/insurances")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsBytes(insuranceCreateRequestMock)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var response = this.mapper.readValue(responseAsString, InsuranceProductResponse.class);

        var createdInsurance = insuranceProductRepository.findById(response.id());

        assertEquals(createdInsurance.get().getId(), createdInsurance.get().getId());
    }


    @Test
    void shouldFindInsuranceWithSuccess() throws Exception {
        var insuranceMock = InsuranceProductMockCreatorUtil.createDomain();
        var createdInsurance = insuranceProductRepository.save(InsuranceEntityMapper.toEntity(insuranceMock));

        var responseAsString = mockMvc.perform(MockMvcRequestBuilders.get("/insurances/" + createdInsurance.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var response = this.mapper.readValue(responseAsString, InsuranceProductResponse.class);

        var foundedInsurance = insuranceProductRepository.findById(response.id());

        assertNotNull(foundedInsurance.get().getId());
    }


    @Test
    void shouldUpdateInsuranceWithSuccess() throws Exception {
        var insuranceMock = InsuranceProductMockCreatorUtil.createDomain();
        var insuranceUpdateRequestMock = CreateInsuranceProductRequest
                .builder()
                .name(insuranceMock.getName())
                .category(insuranceMock.getCategory())
                .priceBase(insuranceMock.getPriceBase())
                .build();


        var createdInsurance = insuranceProductRepository.save(InsuranceEntityMapper.toEntity(insuranceMock));

        var responseAsString = mockMvc.perform(MockMvcRequestBuilders.patch("/insurances/" + createdInsurance.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsBytes(insuranceUpdateRequestMock)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var response = this.mapper.readValue(responseAsString, InsuranceProductResponse.class);

        var foundedInsurance = insuranceProductRepository.findById(response.id());

        assertNotNull(foundedInsurance.get().getId());
        assertEquals(foundedInsurance.get().getCategory(), insuranceUpdateRequestMock.category());
    }

}
