package store.ojuara.produtoapi.mapper.produtoGenericoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.model.Chuteira;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.mapper.ChuteiraMapper;

@Service
@RequiredArgsConstructor
public class ChuteiraProdutoMapper implements ProdutoGenericoMapper{

    private final ChuteiraMapper mapper;

    @Override
    public void mapear(ProdutoGenerico entity, ProdutoGenericoDTO dto) {
        var chuteiraDTO = mapper.toDto((Chuteira) entity);
        dto.setModalidade(chuteiraDTO.getModalidade().name());
        dto.setPontuacao(chuteiraDTO.getPontuacao());
        dto.setTipo(chuteiraDTO.getTipo().name());
        dto.setTipoTrava(chuteiraDTO.getTipoTrava().name());
        dto.setSolado(chuteiraDTO.getSolado());
        dto.setCabedal(chuteiraDTO.getCabedal());
        dto.setPeso(chuteiraDTO.getPeso());
        dto.setMedida(chuteiraDTO.getMedida());
    }
}
