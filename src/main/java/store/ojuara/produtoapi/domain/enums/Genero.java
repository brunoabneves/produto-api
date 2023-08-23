package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {

    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    UNISEX("Unisex");

    private final String descricao;
}
