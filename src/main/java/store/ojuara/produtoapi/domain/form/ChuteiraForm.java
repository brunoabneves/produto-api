package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.GeneroEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteiraEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuteiraForm {

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
    private SetorEnum setor;

    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @NotNull
    private Integer pontuacao;

    @NotNull(message = "Tipo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoChuteiraEnum tipo;

    @NotBlank(message = "Material é obrigatório.")
    private String material;

    @NotBlank(message = "Solado é obrigatório.")
    private String solado;

    @NotBlank(message = "Cabedal é obrigatório.")
    private String cabedal;

    @NotBlank(message = "Peso é obrigatório.")
    private String peso;

    @NotBlank(message = "Medida é obrigatória.")
    private String medida;

    @NotNull(message = "O tipo de trava é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TitpoTravaChuteiraEnum tipoTrava;
}
