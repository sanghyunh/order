package com.sanghyun.order.service.order;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.sanghyun.order.dto.order.OrderDto.OrderRequestDto;
import com.sanghyun.order.entity.order.OrderDetail;
import com.sanghyun.order.entity.order.OrderMaster;
import com.sanghyun.order.entity.order.OrderMaster.OrderStatus;
import com.sanghyun.order.persist.order.OrderDetailPersist;
import com.sanghyun.order.persist.order.OrderMasterPersist;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMasterPersist orderMasterPersist;
    private final OrderDetailPersist orderDetailPersist;


    public void order(OrderRequestDto orderRequestDto, String userId) {
        OrderMaster orderMaster = this.orderMasterPersist.save(
                this.orderMasterPersist.createOrderMaster(userId
                        , orderRequestDto
                                .getGoodsList()
                                .stream()
                                .mapToLong(value -> value.getQuantity() * value.getPrice())
                                .sum()
                        , OrderStatus.ORDER));
        List<OrderDetail> orderDetailList = orderRequestDto.getGoodsList().stream()
                .map(goodsDto -> this.orderDetailPersist.createOrderDetail(orderMaster.getIdx(), goodsDto))
                .collect(Collectors.toList());
        this.orderDetailPersist.saveAll(orderDetailList);

        // TODO : shipping 추가 필요
    }
}
