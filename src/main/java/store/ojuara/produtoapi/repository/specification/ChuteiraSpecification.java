package store.ojuara.produtoapi.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.enums.TipoChuteiraEnum;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteiraEnum;
import store.ojuara.produtoapi.domain.model.Chuteira;

import java.math.BigDecimal;
import java.util.Objects;

public class ChuteiraSpecification {

    public static Specification<Chuteira> filtrar(String nome, String descricao, String fabricante, SituacaoProdutoEnum situacao,
                                                  BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor, SetorEnum setor,
                                                  TipoChuteiraEnum tipoChuteira, String material, TitpoTravaChuteiraEnum tipoTrava) {
        Specification<Chuteira> spec = null;
        Specification<Chuteira> temp = null;

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
        if(Objects.nonNull(setor)){
            temp = filterBySetor(setor);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(tipoChuteira)){
            temp = filterByTipoChuteira(tipoChuteira);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(material)){
            temp = filterByMaterial(material);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(tipoTrava)){
            temp = filterByTipoTrava(tipoTrava);
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

    public static Specification<Chuteira> filterByNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Chuteira> filterByDescricao(String descricao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
    }

    public static Specification<Chuteira> filterByFabricante(String fabricante) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("fabricante")), "%" + fabricante.toLowerCase() + "%");
    }

    public static Specification<Chuteira> filterByFaixaDePreco(BigDecimal valorInicial, BigDecimal valorFinal) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("precoVenda"),valorInicial,valorFinal);
    }

    public static Specification<Chuteira> filterByPontuacao(Integer pontuacao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("pontuacao"), pontuacao);
    }

    public static Specification<Chuteira> filterByCor(String cor) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("cor")), "%" + cor.toLowerCase() + "%");
    }

    public static Specification<Chuteira> filterBySituacao(SituacaoProdutoEnum situacao){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("situacaoProdutoEnum"), situacao);
    }

    public static Specification<Chuteira> filterBySetor(SetorEnum setor){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("setorEnum"), setor);
    }

    public static Specification<Chuteira> filterByTipoChuteira(TipoChuteiraEnum tipoChuteira){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoChuteiraEnum"), tipoChuteira);
    }

    public static Specification<Chuteira> filterByMaterial(String material) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("material"), material);
    }

    public static Specification<Chuteira> filterByTipoTrava(TitpoTravaChuteiraEnum tipoTrava){
        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("tipoTrava"), tipoTrava);
    }
}
