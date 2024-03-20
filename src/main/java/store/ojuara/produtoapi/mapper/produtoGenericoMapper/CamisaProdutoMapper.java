package store.ojuara.produtoapi.mapper.produtoGenericoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.mapper.CamisaMapper;

@Service
@RequiredArgsConstructor
public class CamisaProdutoMapper implements ProdutoGenericoMapper{

    private final CamisaMapper mapper;

    @Override
    public void mapear(ProdutoGenerico entity, ProdutoGenericoDTO dto) {
        var camisaDTO = mapper.toDto((Camisa) entity);
        dto.setTamanhoCamisa(camisaDTO.getTamanhoCamisa().name());
        dto.setAlturaEmCm(camisaDTO.getAlturaEmCm());
        dto.setLarguraEmCm(camisaDTO.getLarguraEmCm());
        dto.setPermitePersonalizacao(camisaDTO.isPermitePersonalizacao());
        dto.setTime(camisaDTO.getTime());
        dto.setTipoGola(camisaDTO.getTipoGola().name());
        dto.setTipoManga(camisaDTO.getTipoManga().name());
        dto.setCamisaDeTime(camisaDTO.isCamisaDeTime());
    }
}
