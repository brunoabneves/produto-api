package store.ojuara.produtoapi.service.camisa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisaEnum;
import store.ojuara.produtoapi.domain.form.CamisaForm;

import java.math.BigDecimal;
import java.util.UUID;

public interface CamisaService {

    CamisaDTO visualizar(Long id);
    CamisaDTO visualizarPorUuid(UUID uuid);
    Page<CamisaDTO> listar(Pageable paginacao);
    CamisaDTO cadastrar(CamisaForm form);
    CamisaDTO atualizar(Long id, CamisaForm form);
    void excluir(Long id);
    Page<CamisaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                                     BigDecimal valorInicial, BigDecimal valorFinal, String cor,
                                                     SetorEnum setor, String material, TamanhoCamisaEnum tamanhoCamisa,
                                                     boolean isCamisaDeTime, String time, Pageable paginacao);
}
