package store.ojuara.produtoapi.mapper.produtoGenericoMapper;

import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;

public interface ProdutoGenericoMapper {
    void mapear(ProdutoGenerico entity, ProdutoGenericoDTO dto);
}
