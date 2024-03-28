package store.ojuara.produtoapi.service.validator;

import store.ojuara.produtoapi.domain.model.ProdutoGenerico;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProdutoGenericoValidator {

    ProdutoGenerico verificarExistencia(Long id);
    ProdutoGenerico verificarExistencia(UUID uuid);
    void validaPrecos(BigDecimal precoFornecedor, BigDecimal precoVenda);
    void validarQuantidade(int qtdProduto, int qtdProdutoExterno);

}
