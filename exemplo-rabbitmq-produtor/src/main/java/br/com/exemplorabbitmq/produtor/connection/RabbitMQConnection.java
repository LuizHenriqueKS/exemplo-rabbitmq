package br.com.exemplorabbitmq.produtor.connection;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.exemplorabbitmq.lib.constant.RabbitMQConst;

@Component
public class RabbitMQConnection {

    private static final String NOME_EXCHANGE = "amq.direct";

    @Autowired
    private AmqpAdmin amqpAdmin;

    @PostConstruct
    private void adicionar() {
        Queue filaEstoque = this.fila(RabbitMQConst.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQConst.FILA_PRECO);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoEstoque = this.relacionamento(filaEstoque, troca);
        Binding ligacaoPreco = this.relacionamento(filaPreco, troca);

        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoEstoque);
        this.amqpAdmin.declareBinding(ligacaoPreco);
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca) {
        String destination = fila.getName();
        DestinationType destinationType = DestinationType.QUEUE;
        String exchange = troca.getName();
        String routingKey = fila.getName();
        Map<String, Object> arguments = null;
        return new Binding(destination, destinationType, exchange, routingKey, arguments);
    }

}
