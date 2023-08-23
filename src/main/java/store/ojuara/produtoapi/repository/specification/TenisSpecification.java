package store.ojuara.produtoapi.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.enums.Modalidade;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.model.Tenis;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class TenisSpecification {

    private final GenericSpecification<Tenis> genericSpecification;

    public Specification<Tenis> filtrar(String nome, String descricao, String fabricante, SituacaoProduto situacao,
                                           BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor, Setor setor,
                                           String material, Modalidade modalidade) {

        Specification<Tenis> spec = genericSpecification.filtrar(nome, descricao, fabricante, situacao, valorInicial, valorFinal, cor, setor, material);
        Specification<Tenis> temp = null;

        if(Objects.nonNull(modalidade)){
            temp = filterByModalidade(modalidade);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(pontuacao)){
            temp = filterByPontuacao(pontuacao);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public Specification<Tenis> filterByPontuacao(Integer pontuacao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("pontuacao"), pontuacao);
    }

    public Specification<Tenis> filterByModalidade(Modalidade modalidade){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("modalidade"), modalidade);
    }
}
