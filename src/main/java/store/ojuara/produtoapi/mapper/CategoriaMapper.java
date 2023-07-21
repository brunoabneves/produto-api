package store.ojuara.produtoapi.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.dto.CategoriaDTO;
import store.ojuara.produtoapi.domain.form.CategoriaForm;
import store.ojuara.produtoapi.domain.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class CategoriaMapper {

    private final ModelMapper mapper;

    public CategoriaDTO toDto(Categoria categoria) {
        return mapper.map(categoria, CategoriaDTO.class);
    }

    public Categoria toEntity(CategoriaForm categoriaForm) {
        return mapper.map(categoriaForm, Categoria.class);
    }

    public Categoria toEntity(Stream<Categoria> categorias) {
        return mapper.map(categorias, Categoria.class);
    }

    public List<CategoriaDTO> toListDTO(List<Categoria> categorias) {
        return categorias.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<CategoriaDTO> toPageDTO(Page<Categoria> page) {
        return page.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<CategoriaDTO> toPage(Page<Categoria> categorias, Pageable pageable) {
        List<CategoriaDTO> dtos = categorias.stream()
                .map(entity -> mapper.map(entity, CategoriaDTO.class))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtos.size());

        return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
    }
}
