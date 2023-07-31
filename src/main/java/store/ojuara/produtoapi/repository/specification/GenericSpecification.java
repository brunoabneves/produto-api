package store.ojuara.produtoapi.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;

import java.math.BigDecimal;
import java.util.Objects;

public class GenericSpecification<T extends ProdutoGenerico> {

    public Specification<T> filtrar(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                    BigDecimal valorInicial, BigDecimal valorFinal, String cor, SetorEnum setor, String material) {
        Specification<T> spec = null;
        Specification<T> temp = null;

        if(Objects.nonNull(nome)){
            temp = filterByNome(nome);
            spec = spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(descricao)){
            temp = filterByDescricao(descricao);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(fabricante)){
            temp = filterByFabricante(fabricante);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(cor)){
            temp = filterByCor(cor);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(situacao)){
            temp = filterBySituacao(situacao);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(setor)){
            temp = filterBySetor(setor);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(material)){
            temp = filterByMaterial(material);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(valorInicial) && Objects.nonNull(valorFinal)){
            temp = filterByFaixaDePreco(valorInicial, valorFinal);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }else if(Objects.nonNull(valorInicial)) {
            temp = filterByFaixaDePreco(valorInicial, new BigDecimal("1000000"));
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        } else if(Objects.nonNull(valorFinal)) {
            temp = filterByFaixaDePreco(BigDecimal.ZERO, valorFinal);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public Specification<T> filterByNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public Specification<T> filterByDescricao(String descricao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
    }

    public Specification<T> filterByFabricante(String fabricante) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("fabricante")), "%" + fabricante.toLowerCase() + "%");
    }

    public Specification<T> filterByFaixaDePreco(BigDecimal valorInicial, BigDecimal valorFinal) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("precoVenda"),valorInicial,valorFinal);
    }

    public Specification<T> filterByCor(String cor) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("cor")), "%" + cor.toLowerCase() + "%");
    }

    public Specification<T> filterBySituacao(SituacaoProdutoEnum situacao){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("situacaoProdutoEnum"), situacao);
    }

    public Specification<T> filterBySetor(SetorEnum setor){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("setorEnum"), setor);
    }

    public Specification<T> filterByMaterial(String material){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("material"), material);
    }
}
