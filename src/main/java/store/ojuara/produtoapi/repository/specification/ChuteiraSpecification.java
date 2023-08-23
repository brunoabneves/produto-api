package store.ojuara.produtoapi.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TipoChuteira;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteira;
import store.ojuara.produtoapi.domain.model.Chuteira;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ChuteiraSpecification {

    private final GenericSpecification<Chuteira> genericSpecification;

    public Specification<Chuteira> filtrar(String nome, String descricao, String fabricante, SituacaoProduto situacao,
                                           BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor, Setor setor,
                                           String material, TipoChuteira tipoChuteira, TitpoTravaChuteira tipoTrava) {

        Specification<Chuteira> spec = genericSpecification.filtrar(nome, descricao, fabricante, situacao, valorInicial, valorFinal, cor, setor, material);
        Specification<Chuteira> temp = null;

        if(Objects.nonNull(pontuacao)){
            temp = filterByPontuacao(pontuacao);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(tipoChuteira)){
            temp = filterByTipoChuteira(tipoChuteira);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(tipoTrava)){
            temp = filterByTipoTrava(tipoTrava);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public Specification<Chuteira> filterByPontuacao(Integer pontuacao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("pontuacao"), pontuacao);
    }

    public Specification<Chuteira> filterByTipoChuteira(TipoChuteira tipoChuteira){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoChuteiraEnum"), tipoChuteira);
    }

    public Specification<Chuteira> filterByTipoTrava(TitpoTravaChuteira tipoTrava){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoTrava"), tipoTrava);
    }
}
