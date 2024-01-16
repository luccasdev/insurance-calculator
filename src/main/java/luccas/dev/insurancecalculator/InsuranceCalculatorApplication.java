package luccas.dev.insurancecalculator;

import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsuranceCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceCalculatorApplication.class, args);
    }

    @Bean
    public OtlpGrpcSpanExporter otlpGrpcSpanExporter() {
        return OtlpGrpcSpanExporter.builder().build();
    }
}
