package com.example.likelion13th.service;

import com.example.likelion13th.domain.Mapping.ProductOrders;
import com.example.likelion13th.domain.Orders;
import com.example.likelion13th.domain.Product;
import com.example.likelion13th.dto.request.ProductDeleteRequestDto;
import com.example.likelion13th.dto.request.ProductUpdateRequestDto;
import com.example.likelion13th.dto.response.ProductResponseDto;
import com.example.likelion13th.exception.MemberNotFoundException;
import com.example.likelion13th.exception.NotBuyerException;
import com.example.likelion13th.repository.MemberRepository;
import com.example.likelion13th.repository.ProductRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.likelion13th.repository.OrderRepository;
import com.example.likelion13th.dto.request.OrderRequestDto;
import com.example.likelion13th.dto.request.OrderItemRequestDto;
import com.example.likelion13th.dto.response.OrderResponseDto;
import com.example.likelion13th.domain.Member;
import com.example.likelion13th.dto.request.ShippingAddressUpdateRequestDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new MemberNotFoundException(dto.getMemberId()));

        // 판매자 권한 확인
        if (!member.isBuyer()) {
            throw new NotBuyerException();
        }

        // 1) 부모 주문 생성 (배송지/상태/구매자 설정)
        Orders orders = dto.toEntity(member);

        // 2) 아이템용 상품을 한 번에 조회
        var productIds = dto.getItems().stream()
                .map(OrderItemRequestDto::getProductId)
                .toList();

        List<Product> products = productRepository.findAllById(productIds);

        // 빠른 매칭을 위한 맵
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        // 3) 각 OrderItemRequestDto → ProductOrders 생성 + 부모에 연결
        for (OrderItemRequestDto item : dto.getItems()) {
            Product product = productMap.get(item.getProductId());
            if (product == null) {
                throw new IllegalArgumentException("상품을 찾을 수 없음: id=" + item.getProductId());
            }

            ProductOrders line = item.toEntity(product); // 수량 포함
            orders.addLine(line); // FK 세팅 + 컬렉션 추가 (연관관계 주의)
        }

        // 4) 저장 (cascade ALL 로 라인도 함께 INSERT)
        Orders saved = orderRepository.save(orders);
        return OrderResponseDto.fromEntity(saved);
    }

    // 구매자별 주문 목록 조회
    public List<OrderResponseDto> getOrdersByBuyer(Long buyerId) {
        return orderRepository.findByBuyerIdAndDeletedFalse(buyerId).stream()
                .map(OrderResponseDto::fromEntity)
                .toList();
    }

    // 단건 주문 상세 조회
    public OrderResponseDto getOrderDetail(Long orderId) {
        Orders orders = orderRepository.findByIdAndDeletedFalse(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문: id=" + orderId));
        return OrderResponseDto.fromEntity(orders);
    }

    // 주문 수정
    @Transactional
    public OrderResponseDto updateShipping(Long orderId, ShippingAddressUpdateRequestDto dto) {
        // 주문 조회
        Orders orders = orderRepository.findByIdAndDeletedFalse(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 삭제된 주문: id=" + orderId));

        // 수정 가능 여부 판단
        if (!orders.canEditShipping()) {
            throw new IllegalStateException("현재 상태(" + orders.getDeliverStatus() + ")에서는 배송지 수정이 불가합니다.");
        }

        orders.updateShipping(dto.toEmbeddable());

        return OrderResponseDto.fromEntity(orders);
    }

    // 주문 삭제
    @Transactional
    public void deleteOrder(Long orderId) {
        // 주문 조회
        Orders orders = orderRepository.findByIdAndDeletedFalse(orderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않거나 이미 삭제된 주문: id=" + orderId));

        if (!orders.canSoftDelete()) {
            throw new IllegalStateException("현재 상태(" + orders.getDeliverStatus() + ")에서는 삭제가 불가합니다.");
        }

        orders.softDelete();
    }
}
