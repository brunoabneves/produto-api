package store.ojuara.produtoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import store.ojuara.produtoapi.domain.dto.ChuteiraDTO;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TipoChuteira;
import store.ojuara.produtoapi.domain.enums.TitpoTravaChuteira;
import store.ojuara.produtoapi.domain.form.ChuteiraForm;
import store.ojuara.produtoapi.domain.form.ChuteiraUpdateForm;
import store.ojuara.produtoapi.service.chuteira.ChuteiraService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/chuteiras")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ChuteiraController {

    private ChuteiraService service;

    @Operation(summary = "Visualizar uma chuteira.", description = "Busca uma chuteira pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<ChuteiraDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.visualizarPorUuid(uuid));
    }

    @Operation(summary = "Lista todas as chuteiras.", description = "Retorna uma lista paginada com todas as chuteiras.")
    @GetMapping("/listar-todos")
    public ResponseEntity<Page<ChuteiraDTO>> listarTodos(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @Operation(summary = "Filtrar chuteiras.", description = "Retorna uma lista de chuteiras de acordo com os filtros especificados.")
    @GetMapping("/buscar")
    public ResponseEntity<Page<ChuteiraDTO>> buscarComFiltros(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "fabricante", required = false) String fabricante,
            @RequestParam(value = "situacao", required = false) SituacaoProduto situacao,
            @RequestParam(value = "valorInicial", required = false) BigDecimal valorInicial,
            @RequestParam(value = "valorFinal", required = false) BigDecimal valorFinal,
            @RequestParam(value = "pontuacao", required = false) Integer pontuacao,
            @RequestParam(value = "cor", required = false) String cor,
            @RequestParam(value = "setor", required = false) Setor setor,
            @RequestParam(value = "tipoChuteira", required = false) TipoChuteira tipoChuteira,
            @RequestParam(value = "material", required = false) String material,
            @RequestParam(value = "tipoTrava", required = false) TitpoTravaChuteira tipoTrava,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        return ResponseEntity.ok(service.pesquisarComFiltrosSpecification(nome, descricao, fabricante, situacao, valorInicial, valorFinal,
                pontuacao, cor, setor,tipoChuteira, material, tipoTrava, paginacao));
    }

    @Operation(summary = "Cadastrar chuteira.")
    @PostMapping("/cadastrar")
    public ResponseEntity<ChuteiraDTO> cadastrar(@Valid @RequestBody ChuteiraForm form, UriComponentsBuilder uriBuilder) {

        var dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/chuteiras/{uuid}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar chuteira por ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ChuteiraDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ChuteiraUpdateForm form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @Operation(summary = "Excluir chuteira por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
