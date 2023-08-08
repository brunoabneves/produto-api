package store.ojuara.produtoapi.domain.model;

import lombok.*;
import store.ojuara.produtoapi.domain.enums.ModalidadeEnum;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteiraEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chuteira extends ProdutoGenerico{

    private Integer pontuacao;
    @Enumerated(EnumType.STRING)
    private TipoChuteiraEnum tipo;
    private String material;
    private String solado;
    private String cabedal;
    private String peso;
    private String medida;
    @Enumerated(EnumType.STRING)
    private TitpoTravaChuteiraEnum tipoTrava;
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidade;
}
