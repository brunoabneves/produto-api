package store.ojuara.produtoapi.domain.model;

import lombok.*;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chuteira extends ProdutoGenerico{

    private String pontuacao;
    private TipoChuteiraEnum tipo;
    private String meterial;
    private String solado;
    private String cabedal;

}
