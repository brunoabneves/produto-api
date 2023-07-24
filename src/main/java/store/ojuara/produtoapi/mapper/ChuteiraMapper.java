package store.ojuara.produtoapi.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.model.Chuteira;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ChuteiraMapper {


    private final ModelMapper mapper;

    public ChuteiraMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ChuteiraDTO toDto(Chuteira chuteira) {
        return mapper.map(chuteira, ChuteiraDTO.class);
    }

    public Chuteira toEntity(ChuteiraForm chuteiraForm) {
        return mapper.map(chuteiraForm, Chuteira.class);
    }

    public Chuteira toEntity(Stream<Chuteira> chuteiras) {
        return mapper.map(chuteiras, Chuteira.class);
    }

    public List<ChuteiraDTO> toListDTO(List<Chuteira> chuteiras) {
        return chuteiras.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<ChuteiraDTO> toPage(Page<Chuteira> chuteiras, Pageable pageable) {
        List<ChuteiraDTO> dtos = chuteiras.stream()
                .map(entity -> mapper.map(entity, ChuteiraDTO.class))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtos.size());

        return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
    }

//    PropertyMap<Chuteira, ChuteiraForm> propertyMap = new PropertyMap<Produto, ProdutoForm>() {
//        @Override
//        protected void configure() {
//            skip().setCategoriaId(null);
//            skip().setSubcategoriaId(null); // Substitua 'setIgnoredProperty' pelo nome da propriedade a ser ignorada
//        }
//    };
}
