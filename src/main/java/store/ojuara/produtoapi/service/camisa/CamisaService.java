package store.ojuara.produtoapi.service.camisa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisa;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.CamisaUpdateForm;

import java.math.BigDecimal;
import java.util.UUID;

public interface CamisaService {

    CamisaDTO visualizar(Long id);
    CamisaDTO visualizarPorUuid(UUID uuid);
    Page<CamisaDTO> listar(Pageable paginacao);
    CamisaDTO cadastrar(CamisaForm form);
    public CamisaDTO atualizar(Long id, CamisaUpdateForm updateForm);
    void excluir(Long id);
    Page<CamisaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProduto situacao,
                                                     BigDecimal valorInicial, BigDecimal valorFinal, String cor,
                                                     Setor setor, String material, TamanhoCamisa tamanhoCamisa,
                                                     boolean isCamisaDeTime, String time, Pageable paginacao);
}
