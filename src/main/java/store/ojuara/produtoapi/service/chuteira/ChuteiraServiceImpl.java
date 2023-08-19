package store.ojuara.produtoapi.service.chuteira;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.enums.*;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.form.ChuteiraUpdateForm;
import store.ojuara.produtoapi.domain.model.Chuteira;
import store.ojuara.produtoapi.mapper.ChuteiraMapper;
import store.ojuara.produtoapi.repository.ChuteiraRepository;
import store.ojuara.produtoapi.repository.specification.ChuteiraSpecification;
import store.ojuara.produtoapi.service.validator.ChuteiraValidator;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChuteiraServiceImpl implements ChuteiraService {

    private final ChuteiraRepository repository;
    private final ChuteiraMapper mapper;
    private final ChuteiraValidator validator;
    private final ChuteiraSpecification specification;

    @Override
    public ChuteiraDTO visualizar(Long id) {
        return mapper.toDto(validator.verificarExistencia(id));
    }

    @Override
    public ChuteiraDTO visualizarPorUuid(UUID uuid) {
        return mapper.toDto(validator.verificarExistencia(uuid));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChuteiraDTO> listar(Pageable paginacao) {
        var pageChuteira = repository.findAll(paginacao);
        return pageChuteira.map(this::toDTO);
    }

    @Override
    public ChuteiraDTO cadastrar(ChuteiraForm form) {
        validator.validarCadastro(form);
        var chuteira = mapper.toModel(form);
        chuteira.setCategoria(CategoriaEnum.CALCADOS);
        chuteira.setModalidade(ModalidadeEnum.FUTEBOL);
        chuteira.setSituacaoProdutoEnum(SituacaoProdutoEnum.CADASTRADO);

        return mapper.toDto(repository.save(chuteira));
    }

    @Override
    public ChuteiraDTO atualizar(Long id, ChuteiraUpdateForm form) {
        var chuteira = validator.verificarExistencia(id);
        mapper.updateChuteiraFromChuteiraUpdateForm(form, chuteira);

        return mapper.toDto(repository.save(chuteira));
    }

    @Override
    public void excluir(Long id) {
        var chuteira = validator.verificarExistencia(id);
        repository.delete(chuteira);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ChuteiraDTO> pesquisarComFiltrosSpecification(String nome, String descricao, String fabricante,
                                                              SituacaoProdutoEnum situacao, BigDecimal valorInicial, BigDecimal valorFinal,
                                                              Integer pontuacao, String cor, SetorEnum setor, TipoChuteiraEnum tipoChuteira,
                                                              String material, TitpoTravaChuteiraEnum tipoTrava, Pageable paginacao) {

        Specification<Chuteira> spec = specification.filtrar(nome, descricao, fabricante,
                situacao, valorInicial, valorFinal, pontuacao, cor, setor, material, tipoChuteira, tipoTrava);
        Page<Chuteira> chuteiraPage = repository.findAll(spec, paginacao);

        return chuteiraPage.map(this::toDTO);
    }

    private ChuteiraDTO toDTO(Chuteira chuteira) {
        return mapper.toDto(chuteira);
    }
}
