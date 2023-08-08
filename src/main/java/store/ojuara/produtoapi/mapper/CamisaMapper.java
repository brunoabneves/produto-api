package store.ojuara.produtoapi.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.CamisaUpdateForm;
import store.ojuara.produtoapi.domain.model.Camisa;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CamisaMapper extends EntityMapper<CamisaDTO, Camisa, CamisaForm> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCamisaFromCamisaUpdateForm(CamisaUpdateForm camisaUpdateForm, @MappingTarget Camisa camisa);
    void updateCamisaFromForm(CamisaForm form, @MappingTarget Camisa entity);
    CamisaForm toForm(Camisa entity);
    Camisa toModel(CamisaForm form);
    List<Camisa> toModel(List<CamisaForm> formList);
    List<Camisa> toModel(Set<CamisaForm> formSet);
    List<CamisaDTO> toDto(List<Camisa> entityList);
}
