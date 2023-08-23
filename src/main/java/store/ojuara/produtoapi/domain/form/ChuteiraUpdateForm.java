package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.Genero;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.TipoChuteira;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuteiraUpdateForm {

    private String nome;
    private String descricao;
    private String marca;
    private String fornecedor;
    @Min(1)
    private BigDecimal precoFornecedor;
    @Min(1)
    private BigDecimal precoVenda;
    private int quantidade;
    private String cor;
    private String imagemUrl;
    private Setor setor;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    /**atributos exclusivos**/
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
}
