package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoChuteiraEnum {

    CAMPO("Campo"),
    FUTSAL("Futsal"),
    SOCIETY("Society"),
    TRAVA_MISTA("Trava mista");

    private final String descricao;
}
