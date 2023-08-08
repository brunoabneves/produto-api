package store.ojuara.produtoapi.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisaEnum;
import store.ojuara.produtoapi.domain.model.Camisa;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CamisaSpecification {

    private final GenericSpecification<Camisa> genericSpecification;

    public Specification<Camisa> filtrar(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                                BigDecimal valorInicial, BigDecimal valorFinal, String cor,
                                                SetorEnum setor, String material, TamanhoCamisaEnum tamanhoCamisa,
                                                boolean isCamisaDeTime, String time) {

        Specification<Camisa> spec = genericSpecification.filtrar(nome,descricao,fabricante,situacao,valorInicial,valorFinal,cor,setor, material);
        Specification<Camisa> temp = null;

        if(Objects.nonNull(tamanhoCamisa)){
            temp = filterByTamanho(tamanhoCamisa);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(isCamisaDeTime && Objects.nonNull(time)){
            temp = filterByTime(time);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public Specification<Camisa> filterByTamanho(TamanhoCamisaEnum tamanho){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tamanhoCamisa"), tamanho);
    }

    public Specification<Camisa> filterByTime(String time){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("time"), time);
    }
}
