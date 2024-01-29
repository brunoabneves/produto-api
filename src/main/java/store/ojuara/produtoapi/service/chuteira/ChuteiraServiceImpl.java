package store.ojuara.produtoapi.service.chuteira;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.form.ChuteiraUpdateForm;
import store.ojuara.produtoapi.domain.model.Chuteira;
import store.ojuara.produtoapi.mapper.ChuteiraMapper;
import store.ojuara.produtoapi.service.ProdutoServiceImpl;

@Service
@Transactional
@RequiredArgsConstructor
public class ChuteiraServiceImpl extends ProdutoServiceImpl<Chuteira, ChuteiraDTO, ChuteiraForm, ChuteiraUpdateForm> {

    private final ChuteiraMapper mapper;

    @Override
    protected ChuteiraDTO convertEntityToDTO(Chuteira entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Chuteira convertFormToEntity(ChuteiraForm form) {
        return mapper.toModel(form);
    }

    @Override
    protected void updateEntityFields(ChuteiraUpdateForm updateForm, Chuteira entity) {
        mapper.updateChuteiraFromChuteiraUpdateForm(updateForm, entity);
    }
//    private final ChuteiraRepository repository;
//    private final ChuteiraValidator validator;
//    private final ChuteiraSpecification specification;


//    @Override
//    @Transactional(readOnly = true)
//    public Page<ChuteiraDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante,
//                                                              SituacaoProduto situacao, BigDecimal valorInicial, BigDecimal valorFinal,
//                                                              Integer pontuacao, String cor, Setor setor, TipoChuteira tipoChuteira,
//                                                              String material, TitpoTravaChuteira tipoTrava, Pageable paginacao) {
//
//        Specification<Chuteira> spec = specification.filtrar(nome, descricao, fabricante,
//                situacao, valorInicial, valorFinal, pontuacao, cor, setor, material, tipoChuteira, tipoTrava);
//        Page<Chuteira> chuteiraPage = repository.findAll(spec, paginacao);
//
//        return chuteiraPage.map(this::toDTO);
//    }
}
