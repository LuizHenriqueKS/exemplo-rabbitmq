package br.com.exemplorabbitmq.produtor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exemplorabbitmq.lib.constant.RabbitMQConst;
import br.com.exemplorabbitmq.lib.dto.EstoqueDto;
import br.com.exemplorabbitmq.produtor.service.RabbitMQService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("estoque")
@Log4j2
public class EstoqueController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    public ResponseEntity<Void> alterarEstoque(@RequestBody EstoqueDto estoqueDto) {
        log.info("Alterando estoque do produto {}", estoqueDto.getCodigoProduto());
        rabbitMQService.enviarMensagem(RabbitMQConst.FILA_ESTOQUE, estoqueDto);
        return ResponseEntity.ok(null);
    }

}
