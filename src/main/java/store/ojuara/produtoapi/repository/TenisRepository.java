package store.ojuara.produtoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import store.ojuara.produtoapi.domain.model.Tenis;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TenisRepository extends JpaRepository<Tenis, Long>, JpaSpecificationExecutor<Tenis> {
    Optional<Tenis> findByUuid(UUID uuid);
}
