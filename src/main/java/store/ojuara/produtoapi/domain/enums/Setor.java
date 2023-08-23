package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Setor {

    ADULTO("Adulto"),
    INFANTIL("Infantil");

    private final String descricao;
}
