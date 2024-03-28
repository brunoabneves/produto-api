package store.ojuara.produtoapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.domain.enums.*;
import store.ojuara.produtoapi.domain.model.Camisa;
import store.ojuara.produtoapi.domain.model.ProdutoGenerico;
import store.ojuara.produtoapi.repository.ProdutoGenericoRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProdutoServiceImplTest {

    @InjectMocks
    @Autowired
    private ProdutoGenericoService service;
    @Mock
    private ProdutoGenericoRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Deve buscar um Produto pelo uuid e mapea-lo para ProdutoDTO quando sucesso.")
    void deveBuscarProdutoDTOPorUuidComSucesso() {
        ProdutoGenerico produtoCreated = camisaCreator();
        ProdutoGenericoDTO dtoCreated = produtoDTOCreator();

        when(repository.findByUuid(produtoCreated.getUuid())).thenReturn(Optional.of(produtoCreated));
        when(service.toDto(produtoCreated)).thenReturn(dtoCreated);
        ProdutoGenericoDTO produtoGenericoDTO = service.getDetalhesProduto(produtoCreated.getUuid());

        assertEquals(dtoCreated, produtoGenericoDTO);
        verify(repository, times(1)).save(any());
        verify(service, times(1)).getDetalhesProduto(produtoCreated.getUuid());
        verifyNoMoreInteractions(repository);
    }

//    @Test
//    void visualizarPorUuid() {
//    }
//
//    @Test
//    void listar() {
//    }
//
//    @Test
//    void cadastrar() {
//    }
//
//    @Test
//    void atualizar() {
//    }
//
//    @Test
//    void excluir() {
//    }

    Camisa camisaCreator(){
        Camisa camisa = new Camisa();
        camisa.setNome("Camisa I Flamengo 23/24 Jogador");
        camisa.setDescricao("Descrição de teste");
        camisa.setMarca("Adidas");
        camisa.setFornecedor("Fornecedor teste");
        camisa.setPrecoFornecedor(BigDecimal.valueOf(80));
        camisa.setPrecoVenda(BigDecimal.valueOf(220));
        camisa.setQuantidade(1);
        camisa.setCor("Rubro/Negro");
        camisa.setImagemUrl("Imagem teste");
        camisa.setCategoria(Categoria.ROUPAS);
        camisa.setSetor(Setor.ADULTO);
        camisa.setGenero(Genero.MASCULINO);
        camisa.setId(1L);
        camisa.setUuid(UUID.fromString("df8c2720-6fac-49c4-8724-f69f17190bdf"));
        camisa.setTamanhoCamisa(TamanhoCamisa.G);
        camisa.setAlturaEmCm(120);
        camisa.setLarguraEmCm(80);
        camisa.setCamisaDeTime(true);
        camisa.setPermitePersonalizacao(false);
        camisa.setTime("Flamengo");
        camisa.setTipoGola(TipoGola.GOLA_REDONDA);
        camisa.setTipoManga(TipoManga.MANGA_CURTA);
        return camisa;
    }

    ProdutoGenericoDTO produtoDTOCreator() {
        Camisa camisa = camisaCreator();
        return ProdutoGenericoDTO.builder()
                .id(camisa.getId())
                .uuid(camisa.getUuid())
                .nome(camisa.getNome())
                .descricao(camisa.getDescricao())
                .marca(camisa.getMarca())
                .fornecedor(camisa.getFornecedor())
                .precoFornecedor(camisa.getPrecoFornecedor())
                .precoVenda(camisa.getPrecoVenda())
                .quantidade(camisa.getQuantidade())
                .cor(camisa.getCor())
                .material(camisa.getMaterial())
                .imagemUrl(camisa.getImagemUrl())
                .categoria(camisa.getCategoria().toString())
                .setor(camisa.getSetor().toString())
                .genero(camisa.getGenero().toString())
                .situacaoProdutoEnum(camisa.getSituacaoProdutoEnum().toString())
                .build();
    }
}