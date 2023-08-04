package store.ojuara.produtoapi.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.enums.ModalidadeEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.model.Tenis;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TenisSpecification {

    private final GenericSpecification<Tenis> genericSpecification;

    public Specification<Tenis> filtrar(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                           BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor, SetorEnum setor,
                                           String material, ModalidadeEnum modalidade) {

        Specification<Tenis> spec = genericSpecification.filtrar(nome, descricao, fabricante, situacao, valorInicial, valorFinal, cor, setor, material);
        Specification<Tenis> temp = null;

        if(Objects.nonNull(modalidade)){
            temp = filterByModalidade(modalidade);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public Specification<Tenis> filterByModalidade(ModalidadeEnum modalidade){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("modalidade"), modalidade);
    }
}
