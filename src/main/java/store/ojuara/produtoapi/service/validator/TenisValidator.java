package store.ojuara.produtoapi.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.model.Tenis;
import store.ojuara.produtoapi.repository.TenisRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenisValidator {

    private final TenisRepository repository;
    private final ValidadorGenerico validadorGenerico;

    @Transactional(readOnly = true)
    public Tenis verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum tênis encontrado para o id informado."));
    }

    @Transactional(readOnly = true)
    public Tenis verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhum tênis encontrado para o uuid informado."));
    }

    public void validarCadastro(TenisForm form) {
        validadorGenerico.validaPrecos(form.getPrecoFornecedor(), form.getPrecoVenda());
    }
}
