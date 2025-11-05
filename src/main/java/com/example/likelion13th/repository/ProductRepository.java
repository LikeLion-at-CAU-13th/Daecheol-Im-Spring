package com.example.likelion13th.repository;

import com.example.likelion13th.domain.Member;
import com.example.likelion13th.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>{
    // 판매자로 상품 목록 조회
    List<Product> findBySeller(Member seller);
}
