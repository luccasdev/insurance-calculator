package luccas.dev.insurancecalculator.infrastructure.controllers;

import io.micrometer.tracing.annotation.NewSpan;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import luccas.dev.insurancecalculator.application.usecases.InsuranceProductInteractor;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request.CreateInsuranceProductRequest;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.request.UpdateInsuranceProductRequest;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response.CreateInsuranceProductResponse;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response.InsuranceProductResponse;
import luccas.dev.insurancecalculator.infrastructure.controllers.dtos.response.UpdateInsuranceProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("insurances")
public class InsuranceProductController {

    private final InsuranceProductInteractor insuranceProductInteractor;

    @NewSpan
    @PostMapping
    public ResponseEntity<CreateInsuranceProductResponse> create(
            @RequestBody @Valid CreateInsuranceProductRequest request
    ) {
        return new ResponseEntity<>(
                CreateInsuranceProductResponse.fromDomain(insuranceProductInteractor.createInsuranceProduct(CreateInsuranceProductRequest.toDomain(request))),
                HttpStatus.CREATED);
    }

    @NewSpan
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateInsuranceProductResponse> partialUpdate(
            @PathVariable UUID id,
            @RequestBody @Valid UpdateInsuranceProductRequest request
    ) {
        return new ResponseEntity<>(
                UpdateInsuranceProductResponse.fromDomain(insuranceProductInteractor.partialUpdateInsuranceProduct(id, UpdateInsuranceProductRequest.toDomain(request))),
                HttpStatus.OK);
    }

    @NewSpan
    @GetMapping("/{id}")
    public ResponseEntity<InsuranceProductResponse> findById(@PathVariable UUID id) {
        return new ResponseEntity<>(
                InsuranceProductResponse.fromDomain(insuranceProductInteractor.findById(id)),
                HttpStatus.OK);
    }
}
