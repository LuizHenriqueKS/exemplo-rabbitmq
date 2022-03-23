package br.com.exemplorabbitmq.consumidor.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.exemplorabbitmq.lib.constant.RabbitMQConst;
import br.com.exemplorabbitmq.lib.dto.EstoqueDto;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class EstoqueConsumer {

    @RabbitListener(queues = RabbitMQConst.FILA_ESTOQUE)
    public void consumir(String mensagem) throws JsonMappingException, JsonProcessingException {
        log.info("Mensagem recebida: " + mensagem);
        EstoqueDto estoqueDto = new ObjectMapper().readValue(mensagem, EstoqueDto.class);
    }

}
