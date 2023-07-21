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
import store.ojuara.produtoapi.domain.dto.SubcategoriaDTO;
import store.ojuara.produtoapi.domain.form.SubcategoriaForm;
import store.ojuara.produtoapi.service.subcategoria.SubcategoriaService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/subcategorias")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SubcategoriaController {

    private SubcategoriaService service;

    @Operation(summary = "Visualizar uma subcategoria.", description = "Busca uma subcategoria pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<SubcategoriaDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.visualizar(uuid));
    }

    @Operation(summary = "Lista todas as subcategorias.", description = "Retorna uma lista paginada com todas as subcategorias.")
    @GetMapping("/listar-todas")
    public ResponseEntity<Page<SubcategoriaDTO>> listarTodos(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @Operation(summary = "Cadastrar subcategoria.")
    @PostMapping("/cadastrar")
    public ResponseEntity<SubcategoriaDTO> cadastrar(@Valid @RequestBody SubcategoriaForm form, UriComponentsBuilder uriBuilder) {

        var subcategoriaDTO = service.cadastrar(form);
        URI uri = uriBuilder.path("/subcategorias/{uuid}").buildAndExpand(subcategoriaDTO.getUuid()).toUri();
        return ResponseEntity.created(uri).body(subcategoriaDTO);
    }

    @Operation(summary = "Atualizar subcategoria por ID.")
    @PutMapping("/{id}")
    public ResponseEntity<SubcategoriaDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody SubcategoriaForm form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @Operation(summary = "Excluir subcategoria por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filtrar subcategorias.", description = "Retorna uma lista de subcategorias de acordo com os filtros especificados.")
    @GetMapping("/buscar")
    public ResponseEntity<Page<SubcategoriaDTO>> buscarComFiltros(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        return ResponseEntity.ok(service.pesquisarComFiltrosSpecification(nome, categoriaId, paginacao));
    }
}
