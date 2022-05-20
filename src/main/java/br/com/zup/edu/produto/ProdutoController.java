package br.com.zup.edu.produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("/produto")
    public ResponseEntity<?> cadastra(@RequestBody ProdutoRequest produtoRequest,
                                      UriComponentsBuilder uriComponentsBuilder){
        if(produtoRepository.existsByCodigo(produtoRequest.getCodigo())){
            throw new ResponseStatusException(HttpStatus
                    .UNPROCESSABLE_ENTITY, "Código do produto já exisente");
        }

        Produto produto = produtoRequest.toModel();

        produtoRepository.save(produto);

        URI location = uriComponentsBuilder.path("/produto/{id}")
                .buildAndExpand(produto.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> errorExceptionHandler(ConstraintViolationException e){
        Map<String,Object> body = Map.of(
                "status", 422,
                "timestamp", LocalDateTime.now(),
                "message", "Código do produto já cadastrado"

        );
        return ResponseEntity.unprocessableEntity().body(body);
    }
}
