package store.ojuara.produtoapi.service.chuteira;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteiraEnum;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.model.Chuteira;
import store.ojuara.produtoapi.repository.ChuteiraRepository;
import store.ojuara.produtoapi.shared.exception.RegraDeNegocioException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChuteiraValidator {

        private final ChuteiraRepository repository;

    @Transactional(readOnly = true)
    public Chuteira verificarExistencia(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum chuteira encontrado para o id informado."));
    }

    @Transactional(readOnly = true)
    public Chuteira verificarExistencia(UUID uuid) {
        return repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhum chuteira encontrado para o uuid informado."));
    }

    /*TODO Adicionar validações neste método. Por hora, tem apenas uma validação.*/
    public void validarCadastro(ChuteiraForm form) {
        validarTipoTrava(form);
    }
    
    private void validarTipoTrava(ChuteiraForm form) {
        boolean isChuteiraComTrava = form.getTipo().equals(TipoChuteiraEnum.FUTEBOL_CAMPO);
        if(isChuteiraComTrava && form.getTipoTrava().equals(TitpoTravaChuteiraEnum.SEM_TRAVAS)){
            throw new RegraDeNegocioException("O tipo de trava de uma chuteira de campo, não pode ser do tipo: "+TitpoTravaChuteiraEnum.SEM_TRAVAS.getDescricao());
        } else if(!isChuteiraComTrava && !form.getTipoTrava().equals(TitpoTravaChuteiraEnum.SEM_TRAVAS)) {
            throw new RegraDeNegocioException("Chuteiras que não sejam de futebol de campo, não devem ter travas.");
        }
    }
}
