package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoForm {

    @NotNull(message = "O nome do produto é obrigatório.")
    private String nome;

    @NotNull(message = "A categoria do produto é obrigatória.")
    private Long categoriaId;

    @NotNull(message = "A subcategoria do produto é obrigatória.")
    private Long subcategoriaId;

    @NotNull
    @NotBlank
    private String descricao;

    private String marca;

    @NotNull
    @NotBlank(message = "A cor do produto é obrigatória.")
    private String fornecedor;

    private String cor;

    @NotNull
    @Min(0)
    private BigDecimal precoFornecedor;

    @NotNull
    @Min(0)
    private BigDecimal precoVenda;

    @NotNull
    @Min(1)
    private int quantidade;

    private String imagemUrl;
}
