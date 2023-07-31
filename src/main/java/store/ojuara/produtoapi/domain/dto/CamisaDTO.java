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
public class CamisaDTO {

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
    private CategoriaEnum categoria;
    @Enumerated(EnumType.STRING)
    private SetorEnum setor;
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;
    @Enumerated(EnumType.STRING)
    private SituacaoProdutoEnum situacaoProdutoEnum;
    @Enumerated(EnumType.STRING)
    private TamanhoCamisaEnum tamanhoCamisa;
    private Integer alturaEmCm;
    private Integer larguraEmCm;
    private boolean isCamisaDeTime;
    private boolean permitePersonalizacao;
    private String time;
    @Enumerated(EnumType.STRING)
    private TipoGolaEnum tipoGola;
    @Enumerated(EnumType.STRING)
    private TipoMangaEnum tipoManga;
}
