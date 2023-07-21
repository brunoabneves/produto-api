package store.ojuara.produtoapi.service.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import store.ojuara.produtoapi.domain.dto.CategoriaDTO;
import store.ojuara.produtoapi.domain.form.CategoriaForm;

import java.util.UUID;

public interface CategoriaService {

    CategoriaDTO visualizar(Long id);
    CategoriaDTO visualizarPorUuid(UUID uuid);
    Page<CategoriaDTO> listar(Pageable paginacao);
    CategoriaDTO cadastrar(CategoriaForm form);
    CategoriaDTO atualizar(Long id, CategoriaForm form);
    void excluir(Long id);
    Page<CategoriaDTO> pesquisarComFiltrosSpecification(String nome, String descricao, Pageable paginacao);
}
