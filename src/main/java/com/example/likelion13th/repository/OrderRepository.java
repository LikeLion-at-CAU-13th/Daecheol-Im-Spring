package com.example.likelion13th.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.likelion13th.domain.Orders;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    // 구매자별 목록 (삭제 제외)
    List<Orders> findByBuyerIdAndDeletedFalse(Long buyerId);

    // 상세 조회 (삭제 제외)
    Optional<Orders> findByIdAndDeletedFalse(Long id);
}
