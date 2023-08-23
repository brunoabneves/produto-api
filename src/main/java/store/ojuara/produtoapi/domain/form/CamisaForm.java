package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CamisaForm {

    @NotBlank(message = "O nome do chuteira é obrigatório.")
    private String nome;

    private String descricao;

    private String marca;

    @NotBlank(message = "A cor do chuteira é obrigatória.")
    private String fornecedor;

    @NotNull
    @Min(0)
    private BigDecimal precoFornecedor;

    @NotNull
    @Min(0)
    private BigDecimal precoVenda;

    @NotNull
    @Min(1)
    private int quantidade;

    private String cor;

    private String imagemUrl;

    @NotNull(message = "O setor da chuteira é obrigatório")
    @Enumerated(EnumType.STRING)
    private Setor setor;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull(message = "O tamanho da camisa é obrigatório")
    @Enumerated(EnumType.STRING)
    private TamanhoCamisa tamanhoCamisa;

    @NotNull
    private Integer alturaEmCm;

    @NotNull
    private Integer larguraEmCm;

    @NotNull
    private boolean camisaDeTime;

    @NotNull
    private boolean permitePersonalizacao;

    private String time;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoGola tipoGola;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoManga tipoManga;
}
