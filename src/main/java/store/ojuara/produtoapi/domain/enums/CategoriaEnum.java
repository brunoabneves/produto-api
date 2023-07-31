package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaEnum {

    ACESSORIOS("Acessórios"),
    ROUPAS("Roupas"),
    CALCADOS("Calçados");

    private final String descricao;
}
