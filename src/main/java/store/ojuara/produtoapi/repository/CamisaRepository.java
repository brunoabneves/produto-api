package store.ojuara.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import store.ojuara.produtoapi.domain.model.Camisa;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CamisaRepository extends JpaRepository<Camisa, Long>, JpaSpecificationExecutor<Camisa> {
    Optional<Camisa> findByUuid(UUID uuid);
}
