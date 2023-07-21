package store.ojuara.produtoapi.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import store.ojuara.produtoapi.domain.model.Subcategoria;

import java.util.Objects;

public class SubcategoriaSpecification {

    public static Specification<Subcategoria> filtrar(String nome, Long categoriaId) {
        Specification<Subcategoria> spec = null;
        Specification<Subcategoria> temp = null;

        if(Objects.nonNull(nome)){
            temp = filterByNome(nome);
            spec=spec != null?Specification.where(spec).and(temp):temp;
        }
        if(Objects.nonNull(categoriaId)){
            temp = filterByCategoriaId(categoriaId);
            spec=spec!=null?Specification.where(spec).and(temp):temp;
        }
        return spec;
    }

    public static Specification<Subcategoria> filterByNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Subcategoria> filterByCategoriaId(Long categoriaId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("categoria").get("id"), categoriaId);
    }
}
