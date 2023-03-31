package com.sanghyun.order.persist.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.sanghyun.order.entity.order.OrderMaster;
import com.sanghyun.order.entity.order.OrderMaster.OrderStatus;
import com.sanghyun.order.repository.order.OrderMasterRepository;
import com.sanghyun.order.util.DateUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderMasterPersist {

    private final OrderMasterRepository orderMasterRepository;
    private final DateUtil dateUtil;

    public OrderMaster save(OrderMaster orderMaster) {
        return this.orderMasterRepository.save(orderMaster);
    }

    public OrderMaster createOrderMaster(
            String userId,
            Long totalPrice,
            OrderStatus orderStatus) {
        return OrderMaster.builder()
                .userId(userId)
                .totalPrice(totalPrice)
                .orderDate(this.dateUtil.convertToFormat(LocalDateTime.now(), "yyyyMMdd"))
                .orderStatus(orderStatus)
                .createUser(userId)
                .build();
    }

    public List<OrderMaster> find(String userId, String startDate, String endDate) {
        return this.orderMasterRepository.findByUserIdAndOrderDateBetween(userId, startDate, endDate);
    }

    public Optional<OrderMaster> findByIdx(Long orderMasterIdx) {
        return this.orderMasterRepository.findById(orderMasterIdx);
    }
}
