package com.sanghyun.order.persist.order;

import org.springframework.stereotype.Service;

import com.sanghyun.order.entity.order.OrderMaster;
import com.sanghyun.order.entity.order.OrderMaster.OrderStatus;
import com.sanghyun.order.repository.order.OrderMasterRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderMasterPersist {

    private final OrderMasterRepository orderMasterRepository;

    public OrderMaster save(OrderMaster orderMaster) {
        return this.orderMasterRepository.save(orderMaster);
    }

    public OrderMaster createOrderMaster(
            String userId
            , Long totalPrice
            , OrderStatus orderStatus) {
        return OrderMaster.builder()
                .userId(userId)
                .totalPrice(totalPrice)
                .orderStatus(orderStatus)
                .build();
    }
}
