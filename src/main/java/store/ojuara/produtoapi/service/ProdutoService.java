package store.ojuara.produtoapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProdutoService<D,F,U>{

    D visualizar(Long id);
    D visualizarPorUuid(UUID uuid);
    Page<D> listar(Pageable paginacao);
    D cadastrar(F form);
    public D atualizar(Long id, U updateForm);
    void excluir(Long id);
}
