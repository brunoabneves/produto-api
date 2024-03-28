package store.ojuara.produtoapi.service.validator.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.repository.ProdutoGenericoRepository;
import store.ojuara.produtoapi.service.validator.ProdutoGenericoValidator;
import store.ojuara.produtoapi.shared.exception.RegraDeNegocioException;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoGenericoValidatorImpl implements ProdutoGenericoValidator {

    private final ProdutoGenericoRepository repository;

    @Transactional(readOnly = true)
    public ProdutoGenerico verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado para o id informado."));
    }

    @Transactional(readOnly = true)
    public ProdutoGenerico verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado para o uuid informado."));
    }

    public void validaPrecos(BigDecimal precoFornecedor, BigDecimal precoVenda) {
        if(precoFornecedor.compareTo(precoVenda) >= 0) {
            throw new RegraDeNegocioException("O preço de fornecedor do produto, não pode ser maior ou igual ao seu preço de venda.");
        }
    }

    public void validarQuantidade(int qtdProduto, int qtdProdutoExterno) {
        if(qtdProduto < qtdProdutoExterno){
            throw new RegraDeNegocioException("A quantidade do produto no pedido não pode ser maior que a quantidade em estoque");
        }
    }
}
