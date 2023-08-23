package store.ojuara.produtoapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisa;
import store.ojuara.produtoapi.domain.enums.TipoGola;
import store.ojuara.produtoapi.domain.enums.TipoManga;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Camisa extends ProdutoGenerico{

    @Enumerated(EnumType.STRING)
    private TamanhoCamisa tamanhoCamisa;
    private Integer alturaEmCm;
    private Integer larguraEmCm;
    private boolean camisaDeTime;
    private boolean permitePersonalizacao;
    private String time;
    @Enumerated(EnumType.STRING)
    private TipoGola tipoGola;
    @Enumerated(EnumType.STRING)
    private TipoManga tipoManga;
}
