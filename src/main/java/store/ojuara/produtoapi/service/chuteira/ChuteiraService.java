package store.ojuara.produtoapi.service.chuteira;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteiraEnum;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;

import java.math.BigDecimal;
import java.util.UUID;

public interface ChuteiraService {
    ChuteiraDTO visualizar(Long id);
    ChuteiraDTO visualizarPorUuid(UUID uuid);
    Page<ChuteiraDTO> listar(Pageable paginacao);
    ChuteiraDTO cadastrar(ChuteiraForm form);
    ChuteiraDTO atualizar(Long id, ChuteiraForm form);
    void excluir(Long id);
    Page<ChuteiraDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante,
                                                       SituacaoProdutoEnum situacao, BigDecimal valorInicial, BigDecimal valorFinal,
                                                       Integer pontuacao, String cor, SetorEnum setor, TipoChuteiraEnum tipoChuteira,
                                                       String material, TitpoTravaChuteiraEnum tipoTrava, Pageable paginacao);
}
