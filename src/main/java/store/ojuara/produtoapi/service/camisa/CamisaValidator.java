package store.ojuara.produtoapi.service.camisa;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.repository.CamisaRepository;
import store.ojuara.produtoapi.service.ValidadorGenerico;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CamisaValidator {

    private final CamisaRepository repository;
    private final ValidadorGenerico validadorGenerico;

    @Transactional(readOnly = true)
    public Camisa verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhuma camisa encontrada para o id informado."));
    }

    @Transactional(readOnly = true)
    public Camisa verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhuma camisa encontrada para o uuid informado."));
    }

    public void validarCadastro(CamisaForm form) {
        validadorGenerico.validaPrecos(form.getPrecoFornecedor(), form.getPrecoVenda());
    }
}
