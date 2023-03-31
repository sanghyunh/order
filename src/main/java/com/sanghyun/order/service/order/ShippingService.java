package com.sanghyun.order.service.order;

import org.springframework.stereotype.Service;

import com.sanghyun.order.dto.order.OrderDto.ShippingDto;
import com.sanghyun.order.entity.order.OrderMaster;
import com.sanghyun.order.persist.order.ShippingPersist;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingPersist shippingPersist;

    public void save(String userId, Long orderMasterIdx, ShippingDto shipping) {
        this.shippingPersist.save(this.shippingPersist.createShipping(userId, orderMasterIdx, shipping));
    }

    public void updateShipping(String userId, OrderMaster master, ShippingDto shippingDto) {
        master.getShipping().updateShipping(userId, shippingDto);
    }
}
