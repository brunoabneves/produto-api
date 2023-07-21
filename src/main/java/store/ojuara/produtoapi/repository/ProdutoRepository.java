package store.ojuara.produtoapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import store.ojuara.produtoapi.domain.model.Produto;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {
    Optional<Produto> findByUuid(UUID uuid);

    Page<Produto> findByCategoriaId(Long categoriaId, Pageable pageable);
}
