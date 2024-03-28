package store.ojuara.produtoapi.service.tenis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.enums.Categoria;
import store.ojuara.produtoapi.domain.enums.Modalidade;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.form.TenisUpdateForm;
import store.ojuara.produtoapi.domain.model.Tenis;
import store.ojuara.produtoapi.mapper.TenisMapper;
import store.ojuara.produtoapi.repository.TenisRepository;
import store.ojuara.produtoapi.repository.specification.TenisSpecification;
import store.ojuara.produtoapi.service.validator.ProdutoGenericoValidator;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TenisServiceImpl implements TenisService{

    private final TenisRepository repository;
    private final TenisMapper mapper;
    private final ProdutoGenericoValidator validator;
    private final TenisSpecification specification;

    @Override
    public TenisDTO visualizar(Long id) {
        return mapper.toDto((Tenis) validator.verificarExistencia(id));
    }

    @Override
    public TenisDTO visualizarPorUuid(UUID uuid) {
        return mapper.toDto((Tenis) validator.verificarExistencia(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenisDTO> listar(Pageable paginacao) {
        var pageTenis = repository.findAll(paginacao);
        return pageTenis.map(this::toDTO);
    }

    @Override
    public TenisDTO cadastrar(TenisForm form) {
        validator.validaPrecos(form.getPrecoFornecedor(), form.getPrecoVenda());
        var tenis = mapper.toModel(form);
        tenis.setCategoria(Categoria.CALCADOS);
        tenis.setSituacaoProdutoEnum(SituacaoProduto.CADASTRADO);

        return mapper.toDto(repository.save(tenis));
    }

    @Override
    public TenisDTO atualizar(Long id, TenisUpdateForm form) {
        var tenis = (Tenis) validator.verificarExistencia(id);
        mapper.updateTenisFromTenisUpdateForm(form, tenis);

        return mapper.toDto(repository.save(tenis));
    }

    @Override
    public void excluir(Long id) {
        var tenis = (Tenis) validator.verificarExistencia(id);
        repository.delete(tenis);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenisDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante, SituacaoProduto situacao,
                                                           BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor,
                                                           Setor setor, String material, Modalidade modalidade, Pageable paginacao) {

        Specification<Tenis> spec = specification.filtrar(nome, descricao, fabricante,
                situacao, valorInicial, valorFinal, pontuacao, cor, setor, material, modalidade);
        Page<Tenis> pageTenis = repository.findAll(spec, paginacao);

        return pageTenis.map(this::toDTO);
    }

    private TenisDTO toDTO(Tenis tenis) {
        return mapper.toDto(tenis);
    }
}
