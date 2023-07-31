package store.ojuara.produtoapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import store.ojuara.produtoapi.domain.enums.CategoriaEnum;
import store.ojuara.produtoapi.domain.enums.GeneroEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class ProdutoGenerico implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid = UUID.randomUUID();

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;

    private String updateReason;
    private String nome;
    private String descricao;
    private String marca;
    private String fornecedor;
    private BigDecimal precoFornecedor;
    private BigDecimal precoVenda;
    private Integer quantidade;
    private String cor;
    private String material;
    private String imagemUrl;

    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    @Enumerated(EnumType.STRING)
    private SetorEnum setor;
    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;
    @Enumerated(EnumType.STRING)
    private SituacaoProdutoEnum situacaoProdutoEnum;
}
