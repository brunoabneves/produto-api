package store.ojuara.produtoapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.ojuara.produtoapi.domain.enums.Modalidade;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("tenis")
public class Tenis extends ProdutoGenerico {

    private Integer pontuacao;
    private String material;
    private String solado;
    private String palmilha;
    private String peso;
    private String medida;
    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
}
