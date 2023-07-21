package store.ojuara.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import store.ojuara.produtoapi.domain.model.Subcategoria;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubcategoriaRepository extends JpaRepository<Subcategoria, Long>, JpaSpecificationExecutor<Subcategoria> {
    Optional<Subcategoria> findByUuid(UUID uuid);
}
