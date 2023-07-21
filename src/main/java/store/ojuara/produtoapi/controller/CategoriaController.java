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
import store.ojuara.produtoapi.domain.dto.CategoriaDTO;
import store.ojuara.produtoapi.domain.form.CategoriaForm;
import store.ojuara.produtoapi.service.categoria.CategoriaService;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoriaController {

    private CategoriaService service;

    @Operation(summary = "Visualizar uma categoria.", description = "Busca uma categoria pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<CategoriaDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.visualizarPorUuid(uuid));
    }

    @Operation(summary = "Lista todas as categorias.", description = "Retorna uma lista paginada com todas as categorias.")
    @GetMapping("/listar-todas")
    public ResponseEntity<Page<CategoriaDTO>> listarTodas(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @Operation(summary = "Cadastrar categoria.")
    @PostMapping("/cadastrar")
    public ResponseEntity<CategoriaDTO> cadastrar(@Valid @RequestBody CategoriaForm form, UriComponentsBuilder uriBuilder) {

        var categoriaDTO = service.cadastrar(form);
        URI uri = uriBuilder.path("/categorias/{uuid}").buildAndExpand(categoriaDTO.getUuid()).toUri();
        return ResponseEntity.created(uri).body(categoriaDTO);
    }

    @Operation(summary = "Atualizar categoria por ID.")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody CategoriaForm form) {
        return ResponseEntity.ok(service.atualizar(id, form));
    }

    @Operation(summary = "Excluir catogoria por ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Filtrar categorias.", description = "Retorna uma lista de categorias de acordo com os filtros especificados.")
    @GetMapping("/buscar")
    public ResponseEntity<Page<CategoriaDTO>> buscarComFiltros(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao,
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {

        return ResponseEntity.ok(service.pesquisarComFiltrosSpecification(nome, descricao, paginacao));
    }
}
