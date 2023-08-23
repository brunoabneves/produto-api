package store.ojuara.produtoapi.repository.specification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisa;
import store.ojuara.produtoapi.domain.model.Camisa;

import java.math.BigDecimal;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CamisaSpecification {

    private final GenericSpecification<Camisa> genericSpecification;

    public Specification<Camisa> filtrar(String nome, String descricao, String fabricante, SituacaoProduto situacao,
                                         BigDecimal valorInicial, BigDecimal valorFinal, String cor,
                                         Setor setor, String material, TamanhoCamisa tamanhoCamisa,
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

    public Specification<Camisa> filterByTamanho(TamanhoCamisa tamanho){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tamanhoCamisa"), tamanho);
    }

    public Specification<Camisa> filterByTime(String time){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("time"), time);
    }
}
