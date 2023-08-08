package store.ojuara.produtoapi.domain.model;

import lombok.*;
import store.ojuara.produtoapi.domain.enums.ModalidadeEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tenis extends ProdutoGenerico {

    private Integer pontuacao;
    private String material;
    private String solado;
    private String palmilha;
    private String peso;
    private String medida;
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidade;
}
