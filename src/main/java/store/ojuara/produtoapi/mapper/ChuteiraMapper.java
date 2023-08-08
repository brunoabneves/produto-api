package store.ojuara.produtoapi.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.form.ChuteiraUpdateForm;
import store.ojuara.produtoapi.domain.model.Chuteira;

@Mapper(componentModel = "spring")
public interface ChuteiraMapper extends EntityMapper<ChuteiraDTO, Chuteira, ChuteiraForm> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateChuteiraFromChuteiraUpdateForm(ChuteiraUpdateForm chuteiraUpdateForm, @MappingTarget Chuteira chuteira);

    void updateChuteiraFromForm(ChuteiraForm form, @MappingTarget Chuteira entity);
}
