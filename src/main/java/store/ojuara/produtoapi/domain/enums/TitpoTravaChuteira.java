package store.ojuara.produtoapi.domain.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TitpoTravaChuteira {

    FIXA("Fixa"),
    MISTA("Mista"),
    REMOVIVEL("Removível"),
    SEM_TRAVAS("Sem travas");

    private final String descricao;
}
