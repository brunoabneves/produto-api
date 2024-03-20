package store.ojuara.produtoapi.kafka.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.avro.pedidorealizado.ItemPedidoAvro;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoResponse {

    private Long idPedido;
    private String uuidProduto;
    private int quantidade;
    private String subtotal;

    public ItemPedidoResponse(ItemPedidoAvro avro) {
        this.idPedido = (long) avro.getIdPedido();
        this.uuidProduto = avro.getUuidProduto().toString();
        this.quantidade = avro.getQuantidade();
        this.subtotal = avro.getSubtotal().toString();
    }

    public static ItemPedidoResponse from(ItemPedidoAvro avro) {
        return new ItemPedidoResponse(avro);
    }
}
