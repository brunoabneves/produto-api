package store.ojuara.produtoapi.domain.model;

import lombok.*;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisaEnum;
import store.ojuara.produtoapi.domain.enums.TipoGolaEnum;
import store.ojuara.produtoapi.domain.enums.TipoMangaEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Camisa extends ProdutoGenerico{

    @Enumerated(EnumType.STRING)
    private TamanhoCamisaEnum tamanhoCamisa;
    private Integer alturaEmCm;
    private Integer larguraEmCm;
    private boolean isCamisaDeTime;
    private boolean permitePersonalizacao;
    private String time;
    @Enumerated(EnumType.STRING)
    private TipoGolaEnum tipoGola;
    @Enumerated(EnumType.STRING)
    private TipoMangaEnum tipoManga;
}
