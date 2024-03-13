package store.ojuara.produtoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.repository.ProdutoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public abstract class ProdutoServiceImpl<T extends ProdutoGenerico,D,F,U> implements ProdutoService<D,F,U>{

    /** TODO adicionar validações com serviço validator **/
    @Autowired
    private ProdutoRepository<T> repository;

    @Override
    public D visualizar(Long id) {
        return convertEntityToDTO(repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado")));
    }

    @Override
    public D visualizarPorUuid(UUID uuid) {
        return convertEntityToDTO(repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado")));
    }

    @Override
    public Page<D> listar(Pageable paginacao) {
        var pageProduto = repository.findAll(paginacao);
        return pageProduto.map(this::convertEntityToDTO);
    }

    @Override
    public D cadastrar(F form) {
        var produto = convertFormToEntity(form);
        return convertEntityToDTO(repository.save(produto));
    }

    @Override
    public D atualizar(Long id, U updateForm) {
        var produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        updateEntityFields(updateForm, produto);

        return convertEntityToDTO(repository.save(produto));
    }

    @Override
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    protected abstract D convertEntityToDTO(T entity);
    protected abstract T convertFormToEntity(F form);
    protected abstract void updateEntityFields(U updateForm, T entity);
}
