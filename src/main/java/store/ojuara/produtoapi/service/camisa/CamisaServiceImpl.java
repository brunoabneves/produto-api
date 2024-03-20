package store.ojuara.produtoapi.service.camisa;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.enums.Categoria;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisa;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.CamisaUpdateForm;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.mapper.CamisaMapper;
import store.ojuara.produtoapi.repository.CamisaRepository;
import store.ojuara.produtoapi.repository.specification.CamisaSpecification;
import store.ojuara.produtoapi.service.validator.CamisaValidator;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CamisaServiceImpl implements CamisaService {

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
        camisa.setCategoria(Categoria.ROUPAS);
        camisa.setSituacaoProdutoEnum(SituacaoProduto.CADASTRADO);

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
    public Page<CamisaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProduto situacao,
                                                            BigDecimal valorInicial, BigDecimal valorFinal, String cor,
                                                            Setor setor, String material, TamanhoCamisa tamanhoCamisa,
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
