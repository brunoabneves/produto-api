package store.ojuara.produtoapi.service.categoria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.model.Categoria;
import store.ojuara.produtoapi.repository.CategoriaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriaValidator {

    private final CategoriaRepository repository;

    public Categoria verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encontrada para o ID informado."));
    }

    public Categoria verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException("Categoria não encontrada para o ID informado."));
    }
}
