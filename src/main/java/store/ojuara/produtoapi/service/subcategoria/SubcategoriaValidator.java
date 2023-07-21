package store.ojuara.produtoapi.service.subcategoria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.model.Subcategoria;
import store.ojuara.produtoapi.repository.SubcategoriaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubcategoriaValidator {

    private final SubcategoriaRepository repository;

    public Subcategoria verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Subcategoria não encontrada para o ID informado."));
    }

    public Subcategoria verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(
                () -> new EntityNotFoundException("Subcategoria não encontrada para o ID informado."));
    }
}
