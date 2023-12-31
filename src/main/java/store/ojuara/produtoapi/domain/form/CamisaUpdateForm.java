package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CamisaUpdateForm {

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

    @Enumerated(EnumType.STRING)
    private Setor setor;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    /** atributos exclusivos **/
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
