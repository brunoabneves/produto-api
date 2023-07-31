package store.ojuara.produtoapi.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.model.Camisa;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CamisaMapper {

    private final ModelMapper mapper;

    public CamisaMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public CamisaDTO toDto(Camisa camisa) {
        return mapper.map(camisa, CamisaDTO.class);
    }

    public Camisa toEntity(CamisaForm camisaForm) {
        return mapper.map(camisaForm, Camisa.class);
    }

    public Camisa toEntity(Stream<Camisa> camisas) {
        return mapper.map(camisas, Camisa.class);
    }

    public List<CamisaDTO> toListDTO(List<Camisa> camisas) {
        return camisas.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<CamisaDTO> toPage(Page<Camisa> camisas, Pageable pageable) {
        List<CamisaDTO> dtos = camisas.stream()
                .map(entity -> mapper.map(entity, CamisaDTO.class))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtos.size());

        return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
    }
}
