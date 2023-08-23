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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChuteiraForm {

    @NotBlank(message = "O nome da chuteira é obrigatório.")
    private String nome;

    private String descricao;

    private String marca;

    @NotBlank(message = "Fornecedor é obrigatório.")
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

    @NotNull(message = "Setor é obrigatório")
    @Enumerated(EnumType.STRING)
    private Setor setor;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @NotNull
    private Integer pontuacao;

    @NotNull(message = "Tipo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoChuteira tipo;

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
    private TitpoTravaChuteira tipoTrava;
}
