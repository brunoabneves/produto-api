package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SetorEnum {

    ADULTO("Adulto"),
    INFANTIL("Infantil");

    private final String descricao;
}
