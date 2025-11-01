package com.example.likelion13th.controller;

import com.example.likelion13th.config.PrincipalHandler;
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

    // 내가 판매하는 상품 목록 토큰으로 조회
    @GetMapping("/my")
    public ResponseEntity<List<ProductResponseDto>> getMyProducts() {
        //토큰에서 username 추출
        String username = PrincipalHandler.getUsernameFromPrincipal();

        // 내 상품 목록 조회
        List<ProductResponseDto> products = productService.getMyProducts(username);

        return ResponseEntity.ok(products);
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
