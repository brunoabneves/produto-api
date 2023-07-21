package store.ojuara.produtoapi.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.model.Produto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProdutoSpecification {

    public static Specification<Produto> filtrar(String nome, String descricao,Long categoriaId, Long subcategoriaId, String fabricante,
                                                 SituacaoProdutoEnum situacao, BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor) {
        Specification<Produto> spec = null;
        Specification<Produto> temp = null;

        if(Objects.nonNull(nome)){
            temp = filterByNome(nome);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(descricao)){
            temp = filterByDescricao(descricao);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(categoriaId)){
            temp = filterByCategoriaId(categoriaId);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(subcategoriaId)){
            temp = filterBySubcategoriaId(subcategoriaId);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(fabricante)){
            temp = filterByFabricante(fabricante);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(pontuacao)){
            temp = filterByPontuacao(pontuacao);
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

    public static Specification<Produto> filterByNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Produto> filterByDescricao(String descricao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
    }

    public static Specification<Produto> filterByFabricante(String fabricante) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("fabricante")), "%" + fabricante.toLowerCase() + "%");
    }

    public static Specification<Produto> filterByFaixaDePreco(BigDecimal valorInicial, BigDecimal valorFinal) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("precoVenda"),valorInicial,valorFinal);
    }

    public static Specification<Produto> filterByPontuacao(Integer pontuacao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("pontuacao"), pontuacao);
    }

    public static Specification<Produto> filterByCor(String cor) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("cor")), "%" + cor.toLowerCase() + "%");
    }

    public static Specification<Produto> filterByCategoriaId(Long categoriaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("categoria").get("id"), categoriaId);
    }

    public static Specification<Produto> filterBySubcategoriaId(Long subcategoriaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("subcategoria").get("id"), subcategoriaId);
    }

    public static Specification<Produto> filterBySituacao(SituacaoProdutoEnum situacao){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("situacaoProdutoEnum"), situacao);
    }
}
