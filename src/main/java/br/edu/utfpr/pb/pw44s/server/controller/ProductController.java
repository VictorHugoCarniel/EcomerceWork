package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.ProductDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.ProductMapper;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("products")
@RestController
public class ProductController {

    private final ProductMapper productMapper;
    private final IProductService productService;

    public ProductController(ProductMapper productMapper, IProductService productService){
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(this.productService.findAll()
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> findByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.productService.findByCategoryId(categoryId)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<ProductDTO> save(
            @RequestBody @Valid ProductDTO productDTO) {
        Product product = this.productService.save(
                productMapper.toEntity(productDTO)
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.productMapper.toDto(product));
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(
            @RequestBody @Valid ProductDTO productDTO) {
        Product product = this.productService.save(
                productMapper.toEntity(productDTO)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.productMapper.toDto(product));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product product = this.productService.findById(id);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(this.productMapper.toDto(product));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        Product product = this.productService.findById(id);
        if(product != null){
            this.productService.deleteById(id);
        }
    }
}
