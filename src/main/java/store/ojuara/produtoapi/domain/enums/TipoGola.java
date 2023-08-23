package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoGola {

    GOLA_REDONDA("Gola Redonda"),
    GOLA_V("Gola em V"),
    GOLA_POLO("Gola Polo"),
    GOLA_BOTAO("Gola com Botão");

    private final String descricao;
}
