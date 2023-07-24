package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaEnum {

    ACESSORIOS("Acessorios"),
    CAMISAS("Camisas"),
    CHUTEIRAS("Chuteiras"),
    TENIS("Tênis");

    private final String descricao;
}
