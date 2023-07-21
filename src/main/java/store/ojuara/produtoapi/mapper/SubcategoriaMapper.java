package store.ojuara.produtoapi.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.dto.CategoriaDTO;
import store.ojuara.produtoapi.domain.dto.SubcategoriaDTO;
import store.ojuara.produtoapi.domain.form.SubcategoriaForm;
import store.ojuara.produtoapi.domain.model.Categoria;
import store.ojuara.produtoapi.domain.model.Subcategoria;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class SubcategoriaMapper {

    private final ModelMapper mapper;

    public SubcategoriaDTO toDto(Subcategoria subcategoria) {
        return mapper.map(subcategoria, SubcategoriaDTO.class);
    }

    public Subcategoria toEntity(SubcategoriaForm subcategoriaForm) {
        return mapper.map(subcategoriaForm, Subcategoria.class);
    }

    public Subcategoria toEntity(Stream<Subcategoria> subcategorias) {
        return mapper.map(subcategorias, Subcategoria.class);
    }

    public List<SubcategoriaDTO> toListDTO(List<Subcategoria> subcategorias) {
        return subcategorias.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<SubcategoriaDTO> toPageDTO(Page<Subcategoria> page) {
        return page.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<SubcategoriaDTO> toPage(Page<Subcategoria> subcategorias, Pageable pageable) {
        List<SubcategoriaDTO> dtos = subcategorias.stream()
                .map(entity -> mapper.map(entity, SubcategoriaDTO.class))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtos.size());

        return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
    }
}
