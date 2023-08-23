package store.ojuara.produtoapi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoProduto {

    ATIVO("Ativo"),
    CADASTRADO("Cadastrado"),
    INATIVO("Inativo"),
    CANCELADO("Cancelado"),
    SEM_ESTOQUE("Sem estoque"),
    PEDIDO_ENCAMINHADO_AO_FORNECEDOR("Pedido encaminhado ao fornecedor");

    private final String descricao;
}
