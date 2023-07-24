package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMangaEnum {

    MANGA_CURTA("Manga longa"),
    MANGA_LONGA("Manga curta"),
    REGATA("Regata");

    private final String descricao;
}
