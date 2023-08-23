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
import store.ojuara.produtoapi.domain.dto.CamisaDTO;
import store.ojuara.produtoapi.domain.enums.Setor;
import store.ojuara.produtoapi.domain.enums.SituacaoProduto;
import store.ojuara.produtoapi.domain.enums.TamanhoCamisa;
import store.ojuara.produtoapi.domain.form.CamisaForm;
import store.ojuara.produtoapi.domain.form.CamisaUpdateForm;
import store.ojuara.produtoapi.service.camisa.CamisaService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/camisas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CamisaController {

    private CamisaService service;

    @Operation(summary = "Visualizar uma camisa.", description = "Busca uma camisa pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<CamisaDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.visualizarPorUuid(uuid));
    }

    @Operation(summary = "Lista todas as camisas.", description = "Retorna uma lista paginada com todas as camisas.")
    @GetMapping("/listar-todos")
    public ResponseEntity<Page<CamisaDTO>> listarTodos(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @Operation(summary = "Filtrar camisas.", description = "Retorna uma lista de camisas de acordo com os filtros especificados.")
    @GetMapping("/buscar")
    public ResponseEntity<Page<CamisaDTO>> buscarComFiltros(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "fabricante", required = false) String fabricante,
            @RequestParam(value = "situacao", required = false) SituacaoProduto situacao,
            @RequestParam(value = "valorInicial", required = false) BigDecimal valorInicial,
            @RequestParam(value = "valorFinal", required = false) BigDecimal valorFinal,
            @RequestParam(value = "cor", required = false) String cor,
            @RequestParam(value = "setor", required = false) Setor setor,
            @RequestParam(value = "material", required = false) String material,
            @RequestParam(value = "tamahoCamisa", required = false) TamanhoCamisa tamahoCamisa,
            @RequestParam(value = "isCamisaDeTime", required = false) boolean isCamisaDeTime,
            @RequestParam(value = "time", required = false) String time,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        return ResponseEntity.ok(service.pesquisarComFiltrosSpecification(nome, descricao, fabricante, situacao, valorInicial, valorFinal,
                cor, setor, material, tamahoCamisa, isCamisaDeTime, time, paginacao));
    }

    @Operation(summary = "Cadastrar camisa.")
    @PostMapping("/cadastrar")
    public ResponseEntity<CamisaDTO> cadastrar(@Valid @RequestBody CamisaForm form, UriComponentsBuilder uriBuilder) {

        var dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/camisas/{uuid}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar camisa por ID.")
    @PutMapping("/{id}")
    public ResponseEntity<CamisaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody CamisaUpdateForm form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @Operation(summary = "Excluir camisa por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
