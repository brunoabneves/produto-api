package store.ojuara.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import store.ojuara.produtoapi.domain.model.Chuteira;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChuteiraRepository extends JpaRepository<Chuteira, Long>, JpaSpecificationExecutor<Chuteira> {
    Optional<Chuteira> findByUuid(UUID uuid);
}
