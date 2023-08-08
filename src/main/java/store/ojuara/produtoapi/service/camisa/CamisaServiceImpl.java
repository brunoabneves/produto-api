package store.ojuara.produtoapi.service.camisa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.enums.CategoriaEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisaEnum;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.CamisaUpdateForm;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.mapper.CamisaMapper;
import store.ojuara.produtoapi.repository.CamisaRepository;
import store.ojuara.produtoapi.repository.specification.CamisaSpecification;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CamisaServiceImpl implements CamisaService{

    private final CamisaMapper mapper;
    private final CamisaValidator validator;
    private final CamisaRepository repository;
    private final CamisaSpecification specification;

    @Override
    public CamisaDTO visualizar(Long id) {
        return mapper.toDto(validator.verificarExistencia(id));
    }

    @Override
    public CamisaDTO visualizarPorUuid(UUID uuid) {
        return mapper.toDto(validator.verificarExistencia(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CamisaDTO> listar(Pageable paginacao) {
        var pageCamisa = repository.findAll(paginacao);
        return pageCamisa.map(this::toDTO);
    }

    @Override
    public CamisaDTO cadastrar(CamisaForm form) {
        validator.validarCadastro(form);
        var camisa = mapper.toModel(form);
        camisa.setCategoria(CategoriaEnum.ROUPAS);
        camisa.setSituacaoProdutoEnum(SituacaoProdutoEnum.CADASTRADO);

        return mapper.toDto(repository.save(camisa));
    }

    @Override
    public CamisaDTO atualizar(Long id, CamisaUpdateForm updateForm) {
        var camisa = validator.verificarExistencia(id);
        mapper.updateCamisaFromCamisaUpdateForm(updateForm, camisa);

        return mapper.toDto(repository.save(camisa));
    }

    @Override
    public void excluir(Long id) {
        var camisa = validator.verificarExistencia(id);
        repository.delete(camisa);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CamisaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                                            BigDecimal valorInicial, BigDecimal valorFinal, String cor,
                                                            SetorEnum setor, String material, TamanhoCamisaEnum tamanhoCamisa,
                                                            boolean isCamisaDeTime, String time, Pageable paginacao) {

        Specification<Camisa> spec = specification.filtrar(nome, descricao, fabricante, situacao, valorInicial, valorFinal,
                cor, setor, material, tamanhoCamisa, isCamisaDeTime, time);
        Page<Camisa> pageCamisa = repository.findAll(spec, paginacao);

        return pageCamisa.map(this::toDTO);
    }

    private CamisaDTO toDTO(Camisa camisa) {
        return mapper.toDto(camisa);
    }
}
