package store.ojuara.produtoapi.service.categoria;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.CategoriaDTO;
import store.ojuara.produtoapi.domain.form.CategoriaForm;
import store.ojuara.produtoapi.domain.model.Categoria;
import store.ojuara.produtoapi.mapper.CategoriaMapper;
import store.ojuara.produtoapi.repository.CategoriaRepository;
import store.ojuara.produtoapi.repository.specification.CategoriaSpecification;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService{

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;
    private  final CategoriaValidator validator;
    private final ModelMapper modelMapper;

    @Override
    public CategoriaDTO visualizar(Long id) {
        return mapper.toDto(validator.verificarExistencia(id));
    }

    @Override
    public CategoriaDTO visualizarPorUuid(UUID uuid) {
        return mapper.toDto(validator.verificarExistencia(uuid));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CategoriaDTO> listar(Pageable paginacao) {
        var pageCategoria = repository.findAll(paginacao);
        return mapper.toPage(pageCategoria, paginacao);
    }

    @Override
    public CategoriaDTO cadastrar(CategoriaForm form) {
        var categoria = mapper.toEntity(form);
        return mapper.toDto(repository.save(categoria));
    }

    @Override
    public CategoriaDTO atualizar(Long id, CategoriaForm form) {
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
    public Page<CategoriaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, Pageable paginacao) {
        Specification<Categoria> spec = CategoriaSpecification.filtrar(nome, descricao);
        Page<Categoria> categoriaPage = repository.findAll(spec, paginacao);

        return mapper.toPage(categoriaPage, paginacao);
    }
}
