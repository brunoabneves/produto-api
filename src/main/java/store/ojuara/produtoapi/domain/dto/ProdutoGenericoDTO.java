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
public class ProdutoGenericoDTO {

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
    private String categoria;
    private String setor;
    private String genero;
    private String situacaoProdutoEnum;
    private String modalidade;
    private Integer pontuacao;
    private String tipo;
    private String tipoTrava;
    private String material;
    private String solado;
    private String cabedal;
    private String peso;
    private String medida;
    private String palmilha;
    private String tamanhoCamisa;
    private Integer alturaEmCm;
    private Integer larguraEmCm;
    private boolean camisaDeTime;
    private boolean permitePersonalizacao;
    private String time;
    private String tipoGola;
    private String tipoManga;
}
