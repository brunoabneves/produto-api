package store.ojuara.produtoapi.service.tenis;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.enums.CategoriaEnum;
import store.ojuara.produtoapi.domain.enums.ModalidadeEnum;
import store.ojuara.produtoapi.domain.enums.SetorEnum;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.model.Tenis;
import store.ojuara.produtoapi.mapper.TenisMapper;
import store.ojuara.produtoapi.repository.TenisRepository;
import store.ojuara.produtoapi.repository.specification.TenisSpecification;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TenisServiceImpl implements TenisService{

    private final TenisRepository repository;
    private final TenisMapper mapper;
    private final TenisValidator validator;
    private final ModelMapper modelMapper;
    private final TenisSpecification specification;

    @Override
    public TenisDTO visualizar(Long id) {
        return mapper.toDto(validator.verificarExistencia(id));
    }

    @Override
    public TenisDTO visualizarPorUuid(UUID uuid) {
        return mapper.toDto(validator.verificarExistencia(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenisDTO> listar(Pageable paginacao) {
        var pageTenis = repository.findAll(paginacao);
        return mapper.toPage(pageTenis, paginacao);
    }

    @Override
    public TenisDTO cadastrar(TenisForm form) {
        validator.validarCadastro(form);
        var tenis = mapper.toEntity(form);
        tenis.setCategoria(CategoriaEnum.CALCADOS);
        tenis.setSituacaoProdutoEnum(SituacaoProdutoEnum.CADASTRADO);

        return mapper.toDto(repository.save(tenis));
    }

    @Override
    public TenisDTO atualizar(Long id, TenisForm form) {
        var tenis = validator.verificarExistencia(id);

        TypeMap<TenisForm, Tenis> typeMap = modelMapper.getTypeMap(TenisForm.class, Tenis.class);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(TenisForm.class, Tenis.class);
        }

        modelMapper.map(form, tenis);

        return mapper.toDto(repository.save(tenis));
    }

    @Override
    public void excluir(Long id) {
        var tenis = validator.verificarExistencia(id);
        repository.delete(tenis);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TenisDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante,SituacaoProdutoEnum situacao,
                                                           BigDecimal valorInicial, BigDecimal valorFinal, Integer pontuacao, String cor,
                                                           SetorEnum setor,String material, ModalidadeEnum modalidade, Pageable paginacao) {

        Specification<Tenis> spec = specification.filtrar(nome, descricao, fabricante,
                situacao, valorInicial, valorFinal, pontuacao, cor, setor, material, modalidade);
        Page<Tenis> tenisPage = repository.findAll(spec, paginacao);

        return mapper.toPage(tenisPage, paginacao);
    }
}
