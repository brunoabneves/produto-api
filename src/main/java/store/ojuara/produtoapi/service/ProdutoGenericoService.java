package store.ojuara.produtoapi.service;

import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.kafka.response.ItemPedidoResponse;

import java.util.UUID;

public interface ProdutoGenericoService {
    ProdutoGenericoDTO getDetalhesProduto(UUID uuid);
    ProdutoGenericoDTO atualizarProduto(ItemPedidoResponse response);
    ProdutoGenericoDTO toDto(ProdutoGenerico entity);
}
