package com.example.likelion13th.controller;

import com.example.likelion13th.dto.request.ProductRequestDto;
import com.example.likelion13th.dto.response.ProductResponseDto;
import com.example.likelion13th.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.likelion13th.dto.request.ProductUpdateRequestDto;
import com.example.likelion13th.dto.request.ProductDeleteRequestDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products") // 공통 경로
public class ProductController {

    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductRequestDto dto) {
        return ResponseEntity.ok(productService.createProduct(dto));
    }

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // 특정 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable("id") Long id,
                                                            @RequestBody ProductUpdateRequestDto dto) {
        return ResponseEntity.ok(productService.updateProduct(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id,
                                                @RequestBody ProductDeleteRequestDto dto) {
        productService.deleteProduct(id, dto);
        return ResponseEntity.ok("상품이 성공적으로 삭제되었습니다.");
    }
}
