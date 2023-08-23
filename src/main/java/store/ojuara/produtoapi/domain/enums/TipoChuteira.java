package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoChuteira {

    FUTEBOL_CAMPO("Futebol de campo"),
    FUTSAL("Futsal"),
    SOCIETY("Society");

    private final String descricao;
}
