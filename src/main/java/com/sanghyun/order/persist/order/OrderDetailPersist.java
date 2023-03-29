package com.sanghyun.order.persist.order;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sanghyun.order.dto.order.OrderDto.GoodsDto;
import com.sanghyun.order.entity.order.OrderDetail;
import com.sanghyun.order.repository.order.OrderDetailRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDetailPersist {

    private OrderDetailRepository orderDetailRepository;

    public OrderDetail createOrderDetail(Long orderMasterIdx, GoodsDto goodsDto) {
        return OrderDetail.builder()
                .orderMasterIdx(orderMasterIdx)
                .goods(goodsDto.getGoods())
                .quantity(goodsDto.getQuantity())
                .goodsType(goodsDto.getGoodsType())
                .build();

    }

    public OrderDetail save(OrderDetail orderDetail) {
        return this.orderDetailRepository.save(orderDetail);
    }

    public void saveAll(List<OrderDetail> orderDetailList) {
        this.orderDetailRepository.saveAll(orderDetailList);
    }
}
