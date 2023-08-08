package store.ojuara.produtoapi.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.form.TenisUpdateForm;
import store.ojuara.produtoapi.domain.model.Tenis;

@Mapper(componentModel = "spring")
public interface TenisMapper extends EntityMapper<TenisDTO, Tenis, TenisForm> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTenisFromTenisUpdateForm(TenisUpdateForm tenisUpdateForm, @MappingTarget Tenis tenis);

    void updateTenisFromForm(TenisForm form, @MappingTarget Tenis entity);
}
