package store.ojuara.produtoapi.service.subcategoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.SubcategoriaDTO;
import store.ojuara.produtoapi.domain.form.SubcategoriaForm;

import java.util.UUID;

public interface SubcategoriaService {

    SubcategoriaDTO visualizar(Long id);
    SubcategoriaDTO visualizar(UUID uuid);
    Page<SubcategoriaDTO> listar(Pageable paginacao);
    SubcategoriaDTO cadastrar(SubcategoriaForm form);
    SubcategoriaDTO atualizar(Long id, SubcategoriaForm form);
    void excluir(Long id);
    Page<SubcategoriaDTO> pesquisarComFiltrosSpecification(String nome, Long categoriaId, Pageable paginacao);
}
