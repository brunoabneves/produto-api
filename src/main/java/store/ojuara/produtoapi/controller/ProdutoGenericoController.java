package store.ojuara.produtoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.ojuara.produtoapi.domain.dto.ProdutoGenericoDTO;
import store.ojuara.produtoapi.service.ProdutoGenericoService;

import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoGenericoController {

    private ProdutoGenericoService service;

    @Operation(summary = "Visualizar um produto.", description = "Busca um produto pelo seu UUID.")
    @GetMapping("/{uuid}")
    public ResponseEntity<ProdutoGenericoDTO> visualizar(@PathVariable UUID uuid) {
        return ResponseEntity.ok(service.getDetalhesProduto(uuid));
    }
}
