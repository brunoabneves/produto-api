package store.ojuara.produtoapi.service.validator;

import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.shared.exception.RegraDeNegocioException;

@Service
public class ProdutoGenericoValidator {

    public void validarQuantidade(int qtdProduto, int qtdProdutoExterno) {
        if(qtdProduto < qtdProdutoExterno){
            throw new RegraDeNegocioException("A quantidade do produto no pedido não pode ser maior que a quantidade em estoque");
        }
    }
}
