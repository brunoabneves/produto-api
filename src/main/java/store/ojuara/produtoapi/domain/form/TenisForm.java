package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.GeneroEnum;
import store.ojuara.produtoapi.domain.enums.ModalidadeEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenisForm {

    @NotBlank(message = "Nome é obrigatório.")
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
    private SetorEnum setor;

    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    @NotNull
    private Integer pontuacao;

    @NotBlank(message = "Material é obrigatório.")
    private String material;

    @NotBlank(message = "Solado é obrigatório.")
    private String solado;

    @NotBlank(message = "Palmilha é obrigatória.")
    private String palmilha;

    private String peso;
    private String medida;

    @NotNull(message = "Modalidade é obrigatória.")
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidade;
}
