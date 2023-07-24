package store.ojuara.produtoapi.service.chuteira;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.enums.*;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.model.Chuteira;
import store.ojuara.produtoapi.mapper.ChuteiraMapper;
import store.ojuara.produtoapi.repository.ChuteiraRepository;
import store.ojuara.produtoapi.repository.specification.ChuteiraSpecification;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChuteiraServiceImpl implements ChuteiraService {

    private final ChuteiraRepository repository;
    private final ChuteiraMapper mapper;
    private final ChuteiraValidator validator;
    private final ModelMapper modelMapper;

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
        return mapper.toPage(pageChuteira, paginacao);
    }

    @Override
    public ChuteiraDTO cadastrar(ChuteiraForm form) {
        validator.validarCadastro(form);
        var chuteira = mapper.toEntity(form);
        chuteira.setCategoria(CategoriaEnum.CHUTEIRAS);
        chuteira.setSituacaoProdutoEnum(SituacaoProdutoEnum.CADASTRADO);

        return mapper.toDto(repository.save(chuteira));
    }

    @Override
    public ChuteiraDTO atualizar(Long id, ChuteiraForm form) {
        var chuteira = validator.verificarExistencia(id);
        modelMapper.map(form, chuteira);

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

        Specification<Chuteira> spec = ChuteiraSpecification.filtrar(nome, descricao, fabricante,
                situacao, valorInicial, valorFinal, pontuacao, cor, setor, tipoChuteira, material, tipoTrava);
        Page<Chuteira> chuteiraPage = repository.findAll(spec, paginacao);

        return mapper.toPage(chuteiraPage, paginacao);
    }
}
