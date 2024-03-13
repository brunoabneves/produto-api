package store.ojuara.produtoapi.service.camisa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.CamisaUpdateForm;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.mapper.CamisaMapper;
import store.ojuara.produtoapi.service.ProdutoServiceImpl;

@Service
@Transactional
@RequiredArgsConstructor
public class CamisaServiceImpl extends ProdutoServiceImpl<Camisa,CamisaDTO, CamisaForm, CamisaUpdateForm> {

    private final CamisaMapper mapper;

    @Override
    protected CamisaDTO convertEntityToDTO(Camisa entity) {
        return mapper.toDto(entity);
    }

    @Override
    protected Camisa convertFormToEntity(CamisaForm form) {
        return mapper.toModel(form);
    }

    @Override
    protected void updateEntityFields(CamisaUpdateForm updateForm, Camisa entity) {
        mapper.updateCamisaFromCamisaUpdateForm(updateForm, entity);
    }
//    private final CamisaValidator validator;
//    private final CamisaRepository repository;
//    private final CamisaSpecification specification;



//    @Override
//    @Transactional(readOnly = true)
//    public Page<CamisaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProduto situacao,
//                                                            BigDecimal valorInicial, BigDecimal valorFinal, String cor,
//                                                            Setor setor, String material, TamanhoCamisa tamanhoCamisa,
//                                                            boolean isCamisaDeTime, String time, Pageable paginacao) {
//
//        Specification<Camisa> spec = specification.filtrar(nome, descricao, fabricante, situacao, valorInicial, valorFinal,
//                cor, setor, material, tamanhoCamisa, isCamisaDeTime, time);
//        Page<Camisa> pageCamisa = repository.findAll(spec, paginacao);
//
//        return pageCamisa.map(this::toDTO);
//    }
}
