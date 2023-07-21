package store.ojuara.produtoapi.service.produto;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.ProdutoDTO;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.form.ProdutoForm;
import store.ojuara.produtoapi.domain.model.Produto;
import store.ojuara.produtoapi.mapper.ProdutoMapper;
import store.ojuara.produtoapi.repository.ProdutoRepository;
import store.ojuara.produtoapi.repository.specification.ProdutoSpecification;
import store.ojuara.produtoapi.service.categoria.CategoriaValidator;
import store.ojuara.produtoapi.service.subcategoria.SubcategoriaValidator;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;
    private final ProdutoValidator validator;
    private final ModelMapper modelMapper;
    private final CategoriaValidator categoriaValidator;
    private final SubcategoriaValidator subcategoriaValidator;


    @Override
    public ProdutoDTO visualizar(Long id) {
        return mapper.toDto(validator.verificarExistencia(id));
    }

    @Override
    public ProdutoDTO visualizarPorUuid(UUID uuid) {
        return mapper.toDto(validator.verificarExistencia(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProdutoDTO> listar(Pageable paginacao) {
        var pageProduto = repository.findAll(paginacao);
        return mapper.toPage(pageProduto, paginacao);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public Page<ProdutoDTO> getProdutosPorCategoria(Long categoriaId, Pageable pageable) {
//        var produtoPage = repository.findByCategoriaId(categoriaId, pageable);
//
//        return mapper.toPage(produtoPage, pageable);
//    }

    @Override
    public ProdutoDTO cadastrar(ProdutoForm form) {
        var categoria = categoriaValidator.verificarExistencia(form.getCategoriaId());
        var subcategoria = subcategoriaValidator.verificarExistencia(form.getSubcategoriaId());
        var produto = mapper.toEntity(form);
        produto.setCategoria(categoria);
        produto.setSubcategoria(subcategoria);
        produto.setSituacaoProdutoEnum(SituacaoProdutoEnum.CADASTRADO);

        return mapper.toDto(repository.save(produto));
    }

    @Override
    public ProdutoDTO atualizar(Long id, ProdutoForm form) {
        var produto = validator.verificarExistencia(id);
        modelMapper.map(form, produto);

        return mapper.toDto(repository.save(produto));
    }

    @Override
    public void excluir(Long id) {
        var produto = validator.verificarExistencia(id);
        repository.delete(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProdutoDTO> pesquisarComFiltrosSpecification(String nome, String descricao, Long categoriaId, Long subcategoriaId, String fabricante,
                                                             SituacaoProdutoEnum situacao, BigDecimal valorInicial, BigDecimal valorFinal,
                                                             Integer pontuacao, String cor, Pageable paginacao) {

        Specification<Produto> spec = ProdutoSpecification.filtrar(nome, descricao, categoriaId, subcategoriaId, fabricante,
                situacao, valorInicial, valorFinal, pontuacao, cor);
        Page<Produto> produtoPage = repository.findAll(spec, paginacao);

        return mapper.toPage(produtoPage, paginacao);
    }
}
