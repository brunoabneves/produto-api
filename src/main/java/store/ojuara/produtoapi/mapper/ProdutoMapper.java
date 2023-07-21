package store.ojuara.produtoapi.mapper;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import store.ojuara.produtoapi.domain.dto.ProdutoDTO;
import store.ojuara.produtoapi.domain.form.ProdutoForm;
import store.ojuara.produtoapi.domain.model.Produto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProdutoMapper {


    private final ModelMapper mapper;

    public ProdutoMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ProdutoDTO toDto(Produto produto) {
        return mapper.map(produto, ProdutoDTO.class);
    }

    public Produto toEntity(ProdutoForm produtoForm) {
        return mapper.map(produtoForm, Produto.class);
    }

    public Produto toEntity(Stream<Produto> produtos) {
        return mapper.map(produtos, Produto.class);
    }

    public List<ProdutoDTO> toListDTO(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Page<ProdutoDTO> toPage(Page<Produto> produtos, Pageable pageable) {
        List<ProdutoDTO> dtos = produtos.stream()
                .map(entity -> mapper.map(entity, ProdutoDTO.class))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtos.size());

        return new PageImpl<>(dtos.subList(start, end), pageable, dtos.size());
    }

    PropertyMap<Produto, ProdutoForm> propertyMap = new PropertyMap<Produto, ProdutoForm>() {
        @Override
        protected void configure() {
            skip().setCategoriaId(null);
            skip().setSubcategoriaId(null); // Substitua 'setIgnoredProperty' pelo nome da propriedade a ser ignorada
        }
    };
}
