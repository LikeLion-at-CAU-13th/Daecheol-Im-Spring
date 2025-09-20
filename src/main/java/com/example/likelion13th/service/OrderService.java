package com.example.likelion13th.service;

import com.example.likelion13th.domain.Orders;
import com.example.likelion13th.exception.NotSellerException;
import com.example.likelion13th.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.likelion13th.repository.OrderRepository;
import com.example.likelion13th.dto.request.OrderRequestDto;
import com.example.likelion13th.dto.response.OrderResponseDto;
import com.example.likelion13th.domain.Member;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원")); //

        // 판매자 권한 확인
        if (!member.isBuyer()) {
            throw new NotSellerException(); //
        }

        Orders orders = dto.toEntity(member); // dto를 실제 엔티티로 변환
        Orders saved = orderRepository.save(orders); // 변환된 엔티티를 데이터베이스에 저장
        return OrderResponseDto.fromEntity(saved);
    }
}
