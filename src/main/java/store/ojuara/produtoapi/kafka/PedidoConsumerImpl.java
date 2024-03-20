package store.ojuara.produtoapi.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import store.ojuara.avro.pedidorealizado.ItemPedidoAvro;
import store.ojuara.produtoapi.kafka.response.ItemPedidoResponse;
import store.ojuara.produtoapi.service.ProdutoGenericoService;

@Service
@RequiredArgsConstructor
public class PedidoConsumerImpl {

    private static final Logger logger = LoggerFactory.getLogger(PedidoConsumerImpl.class);
    private final ProdutoGenericoService produtoGenericoService;

    @KafkaListener(topics = "${mensageria.kafka.topic.pedido.processado}",
            groupId = "${spring.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void consume(ConsumerRecord<String, ItemPedidoAvro> record) throws JsonProcessingException {

        try {
            logger.info("Processando mensagem");
            ItemPedidoResponse itensResponse = avroToResponse(record.value());
            produtoGenericoService.atualizarProduto(itensResponse);
            logger.info(String.valueOf(record.value()));
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem do Kafka: {}", e.getMessage());
        }
    }

    private static ItemPedidoResponse avroToResponse(ItemPedidoAvro itemDePedidoAvro) {
        return ItemPedidoResponse.from(itemDePedidoAvro);
    }
}
