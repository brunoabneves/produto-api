package store.ojuara.produtoapi.service.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.ProdutoDTO;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.form.ProdutoForm;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProdutoService {
    ProdutoDTO visualizar(Long id);
    ProdutoDTO visualizarPorUuid(UUID uuid);
    Page<ProdutoDTO> listar(Pageable paginacao);
//    Page<ProdutoDTO> getProdutosPorCategoria(Long categoriaId, Pageable pageable);
    ProdutoDTO cadastrar(ProdutoForm form);
    ProdutoDTO atualizar(Long id, ProdutoForm form);
    void excluir(Long id);
    Page<ProdutoDTO> pesquisarComFiltrosSpecification(String nome, String descricao, Long categoriaId, Long subcategoriaId, String fabricante,
                                                      SituacaoProdutoEnum situacao, BigDecimal valorInicial, BigDecimal valorFinal,
                                                      Integer pontuacao, String cor, Pageable paginacao);
}
