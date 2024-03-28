package store.ojuara.produtoapi.service.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.enums.TipoChuteira;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteira;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.shared.exception.RegraDeNegocioException;

@Service
@RequiredArgsConstructor
public class ChuteiraValidator {
    
    private void validaTipoTrava(ChuteiraForm form) {
        boolean isChuteiraComTrava = form.getTipo().equals(TipoChuteira.FUTEBOL_CAMPO);
        if(isChuteiraComTrava && form.getTipoTrava().equals(TitpoTravaChuteira.SEM_TRAVAS)){
            throw new RegraDeNegocioException("O tipo de trava de uma chuteira de campo, não pode ser do tipo: "+TitpoTravaChuteira.SEM_TRAVAS.getDescricao());
        } else if(!isChuteiraComTrava && !form.getTipoTrava().equals(TitpoTravaChuteira.SEM_TRAVAS)) {
            throw new RegraDeNegocioException("Chuteiras que não sejam de futebol de campo, não devem ter travas.");
        }
    }
}
