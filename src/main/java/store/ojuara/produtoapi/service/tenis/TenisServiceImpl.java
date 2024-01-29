package store.ojuara.produtoapi.service.tenis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.form.TenisUpdateForm;
import store.ojuara.produtoapi.domain.model.Tenis;
import store.ojuara.produtoapi.mapper.TenisMapper;
import store.ojuara.produtoapi.service.ProdutoServiceImpl;

@Service
@Transactional
@RequiredArgsConstructor
public class TenisServiceImpl extends ProdutoServiceImpl<Tenis,TenisDTO, TenisForm, TenisUpdateForm> {

    private final TenisMapper mapper;

    private TenisDTO toDTO(Tenis tenis) {
        return mapper.toDto(tenis);
    }

    @Override
    protected TenisDTO convertEntityToDTO(Tenis entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Tenis convertFormToEntity(TenisForm form) {
        return mapper.toModel(form);
    }

    @Override
    protected void updateEntityFields(TenisUpdateForm updateForm, Tenis entity) {
        mapper.updateTenisFromTenisUpdateForm(updateForm, entity);
    }
//    private final TenisRepository repository;
//    private final TenisValidator validator;
//    private final TenisSpecification specification;


//    @Override
//    @Transactional(readOnly = true)
//    public Page<TenisDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProduto situacao,
//                                                           BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor,
//                                                           Setor setor, String material, Modalidade modalidade, Pageable paginacao) {
//
//        Specification<Tenis> spec = specification.filtrar(nome, descricao, fabricante,
//                situacao, valorInicial, valorFinal, pontuacao, cor, setor, material, modalidade);
//        Page<Tenis> pageTenis = repository.findAll(spec, paginacao);
//
//        return pageTenis.map(this::toDTO);
//    }
}
