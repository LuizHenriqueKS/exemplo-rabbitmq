package br.com.exemplorabbitmq.produtor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplorabbitmq.lib.constant.RabbitMQConst;
import br.com.exemplorabbitmq.lib.dto.PrecoDto;
import br.com.exemplorabbitmq.produtor.service.RabbitMQService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("preco")
@Log4j2
public class PrecoController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    public ResponseEntity<Void> alterarPreco(@RequestBody PrecoDto precoDto) {
        log.info("Alterando preco do produto {}", precoDto.getCodigoProduto());
        rabbitMQService.enviarMensagem(RabbitMQConst.FILA_PRECO, precoDto);
        return ResponseEntity.ok(null);
    }

}
