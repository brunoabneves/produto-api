package store.ojuara.produtoapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import store.ojuara.produtoapi.domain.enums.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoCompletoDTO {

    private Long id;
    private UUID uuid;
    private String nome;
    private String descricao;
    private String marca;
    private String fornecedor;
    private BigDecimal precoFornecedor;
    private BigDecimal precoVenda;
    private Integer quantidade;
    private String cor;
    private String imagemUrl;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private Setor setor;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @Enumerated(EnumType.STRING)
    private SituacaoProduto situacaoProdutoEnum;

    @Enumerated(EnumType.STRING)
    private TamanhoCamisa tamanhoCamisa;
    private Integer alturaEmCm;
    private Integer larguraEmCm;
    private boolean isCamisaDeTime;
    private boolean permitePersonalizacao;
    private String time;
    @Enumerated(EnumType.STRING)
    private TipoGola tipoGola;
    @Enumerated(EnumType.STRING)
    private TipoManga tipoManga;

    @Enumerated(EnumType.STRING)
    private Modalidade modalidade;
    private Integer pontuacao;
    @Enumerated(EnumType.STRING)
    private TipoChuteira tipo;
    private String material;
    private String solado;
    private String cabedal;
    private String peso;
    private String medida;
    private String palmilha;
}
