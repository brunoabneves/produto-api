package store.ojuara.produtoapi.service.subcategoria;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.SubcategoriaDTO;
import store.ojuara.produtoapi.domain.form.SubcategoriaForm;
import store.ojuara.produtoapi.domain.model.Subcategoria;
import store.ojuara.produtoapi.mapper.SubcategoriaMapper;
import store.ojuara.produtoapi.repository.SubcategoriaRepository;
import store.ojuara.produtoapi.repository.specification.SubcategoriaSpecification;
import store.ojuara.produtoapi.service.categoria.CategoriaValidator;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SubcategoriaServiceImpl implements SubcategoriaService{

    private final SubcategoriaRepository repository;
    private final SubcategoriaMapper mapper;
    private final SubcategoriaValidator validator;
    private final ModelMapper modelMapper;
    private final CategoriaValidator categoriaValidator;

    @Override
    public SubcategoriaDTO visualizar(Long id) {
        return mapper.toDto(validator.verificarExistencia(id));
    }

    @Override
    public SubcategoriaDTO visualizar(UUID uuid) {
        return mapper.toDto(validator.verificarExistencia(uuid));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SubcategoriaDTO> listar(Pageable paginacao) {
        var pageCategoria = repository.findAll(paginacao);
        return mapper.toPage(pageCategoria, paginacao);
    }

    @Override
    public SubcategoriaDTO cadastrar(SubcategoriaForm form) {
        var categoria = categoriaValidator.verificarExistencia(form.getCategoriaId());
        var subcategoria = mapper.toEntity(form);
        subcategoria.setCategoria(categoria);
        return mapper.toDto(repository.save(subcategoria));
    }

    @Override
    public SubcategoriaDTO atualizar(Long id, SubcategoriaForm form) {
        var categoria = validator.verificarExistencia(id);
        modelMapper.map(form, categoria);
        return mapper.toDto(repository.save(categoria));
    }

    @Override
    public void excluir(Long id) {
        var categoria = validator.verificarExistencia(id);
        repository.delete(categoria);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SubcategoriaDTO> pesquisarComFiltrosSpecification(String nome, Long categoriaId, Pageable paginacao) {
        Specification<Subcategoria> spec = SubcategoriaSpecification.filtrar(nome, categoriaId);
        Page<Subcategoria> categoriaPage = repository.findAll(spec, paginacao);

        return mapper.toPage(categoriaPage, paginacao);
    }
}
