package br.com.rabbitmq.broker.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class NfseDto {
    private BigDecimal numNota;
    private String chave;
}
