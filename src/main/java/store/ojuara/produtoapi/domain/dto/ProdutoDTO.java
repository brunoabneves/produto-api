package store.ojuara.produtoapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;
    private UUID uuid;
    private String nome;
    private String descricao;
    private BigDecimal precoFornecedor;
    private BigDecimal precoVenda;
    private Integer quantidade;
    private String marca;
    private String fornecedor;
    private Long categoriaId;
    private Long subcategoriaId;
    private String cor;
    private String imagemUrl;
    @Enumerated(EnumType.STRING)
    private SituacaoProdutoEnum situacaoProdutoEnum;
}
