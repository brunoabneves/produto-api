package store.ojuara.produtoapi.mapper.produtoGenericoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.domain.model.Tenis;
import store.ojuara.produtoapi.mapper.TenisMapper;

@Service
@RequiredArgsConstructor
public class TenisProdutoMapper implements ProdutoGenericoMapper{

    @Autowired
    private final TenisMapper mapper;

    @Override
    public void mapear(ProdutoGenerico entity, ProdutoGenericoDTO dto) {
        var tenisDTO = mapper.toDto((Tenis) entity);
        dto.setModalidade(tenisDTO.getModalidade().name());
        dto.setPontuacao(tenisDTO.getPontuacao());
        dto.setSolado(tenisDTO.getSolado());
        dto.setPalmilha(tenisDTO.getPalmilha());
        dto.setPeso(tenisDTO.getPeso());
        dto.setMedida(tenisDTO.getMedida());
    }
}
