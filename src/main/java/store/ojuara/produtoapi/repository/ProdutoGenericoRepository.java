package store.ojuara.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoGenericoRepository extends JpaRepository<ProdutoGenerico, Long> {
    Optional<ProdutoGenerico> findByUuid(UUID uuid);
}
