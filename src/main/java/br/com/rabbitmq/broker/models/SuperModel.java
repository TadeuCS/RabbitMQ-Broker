package br.com.rabbitmq.broker.models;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SuperModel {
    @NotNull(message = "Número do RPS é obrigatório")
    private BigDecimal nuRps;
    @NotNull(message = "Data da emissao do RPS é obrigatório")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dtEmi;
}
