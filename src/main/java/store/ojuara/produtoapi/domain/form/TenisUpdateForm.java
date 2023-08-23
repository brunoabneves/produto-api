package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.Genero;
import store.ojuara.produtoapi.domain.enums.Modalidade;
import store.ojuara.produtoapi.domain.enums.Setor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenisUpdateForm {

    private String nome;
    private String descricao;
    private String marca;
    private String fornecedor;
    @Min(1)
    private BigDecimal precoFornecedor;
    @Min(1)
    private BigDecimal precoVenda;
    @Min(1)
    private int quantidade;
    private String cor;
    private String imagemUrl;
    @Enumerated(EnumType.STRING)
    private Setor setor;
    @Enumerated(EnumType.STRING)
    private Genero genero;

    /**atributos exclusivos**/
    private Integer pontuacao;
    private String material;
    private String solado;
    private String palmilha;
    private String peso;
    private String medida;
    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
}
