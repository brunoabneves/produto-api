package store.ojuara.produtoapi.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteiraEnum;
import store.ojuara.produtoapi.domain.model.Chuteira;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ChuteiraSpecification {

    private final GenericSpecification<Chuteira> genericSpecification;

    public Specification<Chuteira> filtrar(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                                  BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor, SetorEnum setor,
                                                  String material,TipoChuteiraEnum tipoChuteira, TitpoTravaChuteiraEnum tipoTrava) {

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

    public Specification<Chuteira> filterByTipoChuteira(TipoChuteiraEnum tipoChuteira){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoChuteiraEnum"), tipoChuteira);
    }

    public Specification<Chuteira> filterByTipoTrava(TitpoTravaChuteiraEnum tipoTrava){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoTrava"), tipoTrava);
    }
}
