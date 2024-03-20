package store.ojuara.produtoapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.domain.model.Chuteira;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.domain.model.Tenis;
import store.ojuara.produtoapi.kafka.response.ItemPedidoResponse;
import store.ojuara.produtoapi.mapper.CamisaMapper;
import store.ojuara.produtoapi.mapper.ChuteiraMapper;
import store.ojuara.produtoapi.mapper.TenisMapper;
import store.ojuara.produtoapi.mapper.produtoGenericoMapper.CamisaProdutoMapper;
import store.ojuara.produtoapi.mapper.produtoGenericoMapper.ChuteiraProdutoMapper;
import store.ojuara.produtoapi.mapper.produtoGenericoMapper.TenisProdutoMapper;
import store.ojuara.produtoapi.repository.ProdutoGenericoRepository;
import store.ojuara.produtoapi.service.validator.ProdutoGenericoValidator;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProdutoGenericoServiceImpl implements ProdutoGenericoService{

    private final ProdutoGenericoRepository repository;
    private final ProdutoGenericoValidator validator;
    private final ChuteiraMapper chuteiraMapper;
    private final CamisaMapper camisaMapper;
    private final TenisMapper tenisMapper;

    @Override
    public ProdutoGenericoDTO getDetalhesProduto(UUID uuid) {
        var produto = repository.findByUuid(uuid).orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado"));
        return toDto(produto);
    }

    @Override
    public ProdutoGenericoDTO atualizarProduto(ItemPedidoResponse response) {
        var produto = repository.findByUuid(UUID.fromString(response.getUuidProduto()))
                .orElseThrow(() -> new EntityNotFoundException("Nenhum produto encontrado"));
        produto.setSituacaoProdutoEnum(SituacaoProduto.PEDIDO_ENCAMINHADO_AO_FORNECEDOR);
        validator.validarQuantidade(produto.getQuantidade(), response.getQuantidade());
        var novaQtd = produto.getQuantidade() - response.getQuantidade();
        if(novaQtd == 0) {
            produto.setSituacaoProdutoEnum(SituacaoProduto.SEM_ESTOQUE);
        }
        produto.setQuantidade(novaQtd);

        return toDto(produto);
    }

    public ProdutoGenericoDTO toDto(ProdutoGenerico entity) {

        if ( entity == null ) {
            return null;
        }

        var produtoGenericoDTO = ProdutoGenericoDTO.builder()
                .id( entity.getId() )
                .uuid( entity.getUuid() )
                .nome( entity.getNome() )
                .descricao( entity.getDescricao() )
                .marca( entity.getMarca() )
                .fornecedor( entity.getFornecedor() )
                .precoFornecedor( entity.getPrecoFornecedor() )
                .precoVenda( entity.getPrecoVenda() )
                .quantidade( entity.getQuantidade() )
                .cor( entity.getCor() )
                .imagemUrl( entity.getImagemUrl() )
                .categoria( entity.getCategoria().name() )
                .setor( entity.getSetor().name() )
                .genero( entity.getGenero().name() )
                .situacaoProdutoEnum( entity.getSituacaoProdutoEnum().name() )
                .material( entity.getMaterial() ).build();

        return mapearSubProdutos(entity, produtoGenericoDTO);
    }

    private ProdutoGenericoDTO mapearSubProdutos(ProdutoGenerico entity, ProdutoGenericoDTO dto) {

        if(entity instanceof Chuteira) {
            new ChuteiraProdutoMapper(chuteiraMapper).mapear(entity, dto);
        } else if (entity instanceof Tenis) {
            new TenisProdutoMapper(tenisMapper).mapear(entity, dto);
        } else if (entity instanceof Camisa) {
            new CamisaProdutoMapper(camisaMapper).mapear(entity, dto);
        } else {
            throw new IllegalArgumentException("Tipo de produto não suportado");
        }

        return dto;
    }
}
