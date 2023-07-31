package store.ojuara.produtoapi.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.model.Tenis;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TenisMapper {

    private final ModelMapper mapper;

    public TenisMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public TenisDTO toDto(Tenis tenis) {
        return mapper.map(tenis, TenisDTO.class);
    }

    public Tenis toEntity(TenisForm tenisForm) {
        return mapper.map(tenisForm, Tenis.class);
    }

    public Tenis toEntity(Stream<Tenis> teniss) {
        return mapper.map(teniss, Tenis.class);
    }

    public List<TenisDTO> toListDTO(List<Tenis> teniss) {
        return teniss.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<TenisDTO> toPage(Page<Tenis> teniss, Pageable pageable) {
        List<TenisDTO> dtos = teniss.stream()
                .map(entity -> mapper.map(entity, TenisDTO.class))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtos.size());

        return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
    }
}
