package store.ojuara.produtoapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.shared.exception.RegraDeNegocioException;

import java.math.BigDecimal;

/*TODO Adicionar validações genéricas nesta classe. Por hora, tem apenas uma validação.*/
@Service
@RequiredArgsConstructor
public class ValidadorGenerico {

    public void validaPrecos(BigDecimal precoFornecedor, BigDecimal precoVenda) {
        if(precoFornecedor.compareTo(precoVenda) >= 0) {
            throw new RegraDeNegocioException("O preço de fornecedor do produto, não pode ser maior ou igual ao seu preço de venda.");
        }
    }
}
