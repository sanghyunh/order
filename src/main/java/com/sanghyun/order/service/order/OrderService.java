package com.sanghyun.order.service.order;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.dto.order.OrderDto.GoodsDto;
import com.sanghyun.order.dto.order.OrderDto.OrderFindResponseDto;
import com.sanghyun.order.dto.order.OrderDto.OrderPostRequestDto;
import com.sanghyun.order.dto.order.OrderDto.OrderPutRequestDto;
import com.sanghyun.order.entity.order.OrderDetail;
import com.sanghyun.order.entity.order.OrderMaster;
import com.sanghyun.order.entity.order.OrderMaster.OrderStatus;
import com.sanghyun.order.entity.order.Shipping.ShippingStatus;
import com.sanghyun.order.exception.CommonException;
import com.sanghyun.order.persist.order.OrderDetailPersist;
import com.sanghyun.order.persist.order.OrderMasterPersist;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMasterPersist orderMasterPersist;
    private final OrderDetailPersist orderDetailPersist;
    private final ShippingService shippingService;

    public void order(OrderPostRequestDto orderPostRequestDto, String userId) {
        OrderMaster orderMaster = this.saveOrderMaster(userId,
                orderPostRequestDto
                        .getGoodsList()
                        .stream()
                        .mapToLong(value -> value.getQuantity() * value.getPrice())
                        .sum());
        List<OrderDetail> orderDetailList = orderPostRequestDto.getGoodsList().stream()
                .map(goodsDto -> this.saveOrderDetail(userId, orderMaster.getIdx(), goodsDto))
                .collect(Collectors.toList());
        this.saveOrderDetailAll(orderDetailList);
        this.shippingService.save(userId, orderMaster.getIdx(), orderPostRequestDto.getShipping());
    }

    public OrderMaster saveOrderMaster(
            String userId,
            Long totalPrice) {
        return this.orderMasterPersist.save(
                this.orderMasterPersist.createOrderMaster(userId
                        , totalPrice
                        , OrderStatus.ORDER));
    }

    public OrderDetail saveOrderDetail(String userId, Long orderMasterIdx, GoodsDto goodsDto) {
        return this.orderDetailPersist.createOrderDetail(userId, orderMasterIdx, goodsDto);
    }

    public void saveOrderDetailAll(List<OrderDetail> orderDetailList) {
        this.orderDetailPersist.saveAll(orderDetailList);
    }

    public OrderFindResponseDto findOrder(String startDate, String endDate, String userId) {

        List<OrderMaster> orderMasterList = this.findOrderMaster(userId, startDate, endDate);
        return OrderFindResponseDto.of(orderMasterList);
    }

    public List<OrderMaster> findOrderMaster(String userId, String startDate, String endDate) {
        return this.orderMasterPersist.find(userId, startDate, endDate);
    }

    @Transactional
    public void putOrder(OrderPutRequestDto orderPutRequestDto, String userId) {
        OrderMaster master = this.findOrderMaster(orderPutRequestDto.getOrderIdx())
                .filter(orderMaster -> orderMaster.getCreateUser().equals(userId)
                        && orderMaster.getShipping().getShippingStatus().equals(ShippingStatus.READY))
                .orElseThrow(() -> new CommonException(Errors.NOT_POSSIBLE_UPDATE_ORDER));
        this.shippingService.updateShipping(userId, master, orderPutRequestDto.getShippingDto());
    }

    public Optional<OrderMaster> findOrderMaster(Long orderMasterIdx) {
        return this.orderMasterPersist.findByIdx(orderMasterIdx);
    }
}
