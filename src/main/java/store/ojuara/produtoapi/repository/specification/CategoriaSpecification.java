package store.ojuara.produtoapi.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import store.ojuara.produtoapi.domain.model.Categoria;
import store.ojuara.produtoapi.domain.model.Produto;

import java.util.Objects;

public class CategoriaSpecification {

    public static Specification<Categoria> filtrar(String nome, String descricao) {
        Specification<Categoria> spec = null;
        Specification<Categoria> temp = null;

        if(Objects.nonNull(nome)){
            temp = filterByNome(nome);
            spec=spec != null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(descricao)){
            temp = filterByDescricao(descricao);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public static Specification<Categoria> filterByNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Categoria> filterByDescricao(String descricao) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
    }
}
