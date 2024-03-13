package store.ojuara.produtoapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import store.ojuara.produtoapi.domain.enums.Modalidade;
import store.ojuara.produtoapi.domain.enums.TipoChuteira;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteira;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("chuteira")
public class Chuteira extends ProdutoGenerico{

    private Integer pontuacao;
    @Enumerated(EnumType.STRING)
    private TipoChuteira tipo;
    private String material;
    private String solado;
    private String cabedal;
    private String peso;
    private String medida;
    @Enumerated(EnumType.STRING)
    private TitpoTravaChuteira tipoTrava;
    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
}
