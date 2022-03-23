package br.com.exemplorabbitmq.produtor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.exemplorabbitmq.produtor.exception.BadRequestException;

@Service
public class RabbitMQService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public <T> void enviarMensagem(String nomeFila, T mensagem) {
        try {
            String mensagemJSON = new ObjectMapper().writeValueAsString(mensagem);
            rabbitTemplate.convertAndSend(nomeFila, mensagemJSON);
        } catch (JsonProcessingException e) {
            throw new BadRequestException(e);
        }
    }

}
