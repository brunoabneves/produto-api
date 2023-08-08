package store.ojuara.produtoapi.service.tenis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.enums.ModalidadeEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.form.TenisUpdateForm;

import java.math.BigDecimal;
import java.util.UUID;

public interface TenisService {

    TenisDTO visualizar(Long id);
    TenisDTO visualizarPorUuid(UUID uuid);
    Page<TenisDTO> listar(Pageable paginacao);
    TenisDTO cadastrar(TenisForm form);
    TenisDTO atualizar(Long id, TenisUpdateForm form);
    void excluir(Long id);
    Page<TenisDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante,
                                                    SituacaoProdutoEnum situacao, BigDecimal valorInicial, BigDecimal valorFinal,
                                                    Integer pontuacao, String cor, SetorEnum setor, String material, ModalidadeEnum modalidade, Pageable paginacao);
}
