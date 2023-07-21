package store.ojuara.produtoapi.service.produto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.model.Produto;
import store.ojuara.produtoapi.repository.ProdutoRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoValidator {

    private final ProdutoRepository repository;

    @Transactional(readOnly = true)
    public Produto verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado para o id informado."));
    }

    @Transactional(readOnly = true)
    public Produto verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado para o uuid informado."));
    }
}
