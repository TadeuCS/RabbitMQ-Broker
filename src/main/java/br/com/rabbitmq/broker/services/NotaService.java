package br.com.rabbitmq.broker.services;

import br.com.rabbitmq.broker.dtos.NfseDto;
import br.com.rabbitmq.broker.models.SuperModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotaService {

    public NfseDto sendToExternalAPI(String message){
        try {
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            SuperModel superModel = mapper.readValue(message, SuperModel.class);
            System.out.println("Recebi a requisição com o SuperModel: "+superModel);
            System.out.println("Enviei para a API externa e obtive como retorno um objeto de NfseModel no padrão documentado");
            System.out.println("Fiz um mapeamento dos dados obtidos no Model para o NfseDto esperado no projeto API");
            return NfseDto.builder()
                    .numNota(superModel.getNuRps())
                    .chave("3131313233232365566556655665655656566565"+superModel.getNuRps())
                    .build();
        }catch (Exception e){
            throw new RuntimeException("Erro ao executar o envio para API Externa!",e);
        }
    }
}
