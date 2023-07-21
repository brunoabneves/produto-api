package store.ojuara.produtoapi.domain.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubcategoriaForm {

    @NotBlank
    private String nome;

    @NotNull
    private Long categoriaId;
}
