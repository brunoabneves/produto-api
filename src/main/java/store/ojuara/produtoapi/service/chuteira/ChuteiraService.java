package store.ojuara.produtoapi.service.chuteira;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TipoChuteira;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteira;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.form.ChuteiraUpdateForm;

import java.math.BigDecimal;
import java.util.UUID;

public interface ChuteiraService {
    ChuteiraDTO visualizar(Long id);
    ChuteiraDTO visualizarPorUuid(UUID uuid);
    Page<ChuteiraDTO> listar(Pageable paginacao);
    ChuteiraDTO cadastrar(ChuteiraForm form);
    ChuteiraDTO atualizar(Long id, ChuteiraUpdateForm form);
    void excluir(Long id);
    Page<ChuteiraDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante,
                                                       SituacaoProduto situacao, BigDecimal valorInicial, BigDecimal valorFinal,
                                                       Integer pontuacao, String cor, Setor setor, TipoChuteira tipoChuteira,
                                                       String material, TitpoTravaChuteira tipoTrava, Pageable paginacao);
}
