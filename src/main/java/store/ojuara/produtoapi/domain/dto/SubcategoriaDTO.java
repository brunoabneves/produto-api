package store.ojuara.produtoapi.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaDTO {

    private Long id;
    private UUID uuid;
    private String nome;
    private Long categoriaId;
}
