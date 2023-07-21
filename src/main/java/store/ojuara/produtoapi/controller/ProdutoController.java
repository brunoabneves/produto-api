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
import store.ojuara.produtoapi.domain.dto.ProdutoDTO;
import store.ojuara.produtoapi.domain.enums.SituacaoProdutoEnum;
import store.ojuara.produtoapi.domain.form.ProdutoForm;
import store.ojuara.produtoapi.service.produto.ProdutoService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoController {

    private ProdutoService service;

    @Operation(summary = "Visualizar um produto.", description = "Busca um produto pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<ProdutoDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.visualizarPorUuid(uuid));
    }

    @Operation(summary = "Lista todos os produtos.", description = "Retorna uma lista paginada com todos os produtos.")
    @GetMapping("/listar-todos")
    public ResponseEntity<Page<ProdutoDTO>> listarTodos(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

//    @Operation(summary = "Lista todos os produtos filtrados", description = "Retorna uma lista paginada com todos os produtos.")
//    @GetMapping("/categorias/{categoriaId}")
//    public ResponseEntity<Page<ProdutoDTO>> gerProdutosPorCategoria(@PathVariable Long categoriaId,
//                                                                    @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
//        return ResponseEntity.ok(service.getProdutosPorCategoria(categoriaId, paginacao));
//    }

    @Operation(summary = "Filtrar produtos.", description = "Retorna uma lista de produtos de acordo com os filtros especificados.")
    @GetMapping("/buscar")
    public ResponseEntity<Page<ProdutoDTO>> buscarComFiltros(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
            @RequestParam(value = "subcategoriaId", required = false) Long subcategoriaId,
            @RequestParam(value = "fabricante", required = false) String fabricante,
            @RequestParam(value = "situacao", required = false) SituacaoProdutoEnum situacao,
            @RequestParam(value = "valorInicial", required = false) BigDecimal valorInicial,
            @RequestParam(value = "valorFinal", required = false) BigDecimal valorFinal,
            @RequestParam(value = "pontuacao", required = false) Integer pontuacao,
            @RequestParam(value = "cor", required = false) String cor,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        return ResponseEntity.ok(service.pesquisarComFiltrosSpecification(nome, descricao, categoriaId, subcategoriaId,
                fabricante, situacao, valorInicial, valorFinal, pontuacao, cor, paginacao));
    }

    @Operation(summary = "Cadastrar produto.")
    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoDTO> cadastrar(@Valid @RequestBody ProdutoForm form, UriComponentsBuilder uriBuilder) {

        var produtoDTO = service.cadastrar(form);
        URI uri = uriBuilder.path("/produtos/{uuid}").buildAndExpand(produtoDTO.getUuid()).toUri();
        return ResponseEntity.created(uri).body(produtoDTO);
    }

    @Operation(summary = "Atualizar produto por ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProdutoForm form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @Operation(summary = "Excluir produto por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
