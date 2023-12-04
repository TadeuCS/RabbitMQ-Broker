package br.com.rabbitmq.broker.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SuperModel {
    @NotNull(message = "Número do RPS é obrigatório")
    private BigDecimal nuRps;
    @NotNull(message = "Data da emissao do RPS é obrigatório")
    private LocalDate dtEmi;
}
