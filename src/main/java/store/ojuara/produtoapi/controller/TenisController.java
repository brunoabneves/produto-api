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
import store.ojuara.produtoapi.domain.dto.TenisDTO;
import store.ojuara.produtoapi.domain.form.TenisForm;
import store.ojuara.produtoapi.domain.form.TenisUpdateForm;
import store.ojuara.produtoapi.service.tenis.TenisServiceImpl;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tenis")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class TenisController {

    private TenisServiceImpl service;

    @Operation(summary = "Visualizar um tênis.", description = "Busca um tênis pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<TenisDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.visualizarPorUuid(uuid));
    }

    @Operation(summary = "Lista todos os tênis.", description = "Retorna uma lista paginada com todos os tênis.")
    @GetMapping("/listar-todos")
    public ResponseEntity<Page<TenisDTO>> listarTodos(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

//    @Operation(summary = "Filtrar tênis.", description = "Retorna uma lista de tênis de acordo com os filtros especificados.")
//    @GetMapping("/buscar")
//    public ResponseEntity<Page<TenisDTO>> buscarComFiltros(
//            @RequestParam(value = "nome", required = false) String nome,
//            @RequestParam(value = "descricao", required = false) String descricao,
//            @RequestParam(value = "fabricante", required = false) String fabricante,
//            @RequestParam(value = "situacao", required = false) SituacaoProduto situacao,
//            @RequestParam(value = "valorInicial", required = false) BigDecimal valorInicial,
//            @RequestParam(value = "valorFinal", required = false) BigDecimal valorFinal,
//            @RequestParam(value = "pontuacao", required = false) Integer pontuacao,
//            @RequestParam(value = "cor", required = false) String cor,
//            @RequestParam(value = "setor", required = false) Setor setor,
//            @RequestParam(value = "material", required = false) String material,
//            @RequestParam(value = "modalidade", required = false) Modalidade modalidade,
//            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
//
//        return ResponseEntity.ok(service.pesquisarComFiltrosSpecification(nome, descricao, fabricante, situacao, valorInicial, valorFinal,
//                pontuacao, cor, setor, material, modalidade, paginacao));
//    }

    @Operation(summary = "Cadastrar tênis.")
    @PostMapping("/cadastrar")
    public ResponseEntity<TenisDTO> cadastrar(@Valid @RequestBody TenisForm form, UriComponentsBuilder uriBuilder) {

        var dto = service.cadastrar(form);
        URI uri = uriBuilder.path("/tenis/{uuid}").buildAndExpand(dto.getUuid()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @Operation(summary = "Atualizar tênis por ID.")
    @PutMapping("/{id}")
    public ResponseEntity<TenisDTO> atualizar(@PathVariable Long id, @Valid @RequestBody TenisUpdateForm form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @Operation(summary = "Excluir tênis por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
