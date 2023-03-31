package com.sanghyun.order.persist.order;

import org.springframework.stereotype.Service;

import com.sanghyun.order.dto.order.OrderDto.ShippingDto;
import com.sanghyun.order.entity.order.Shipping;
import com.sanghyun.order.entity.order.Shipping.ShippingStatus;
import com.sanghyun.order.repository.order.ShippingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingPersist {

    private final ShippingRepository shippingRepository;

    public Shipping createShipping(String userId, Long orderMasterIdx, ShippingDto shipping) {
        return Shipping.builder()
                .orderMasterIdx(orderMasterIdx)
                .recipient(shipping.getRecipient())
                .tel(shipping.getTel())
                .zonecode(shipping.getZonecode())
                .roadAddress(shipping.getRoadAddress())
                .jibunAddress(shipping.getJibunAddress())
                .addressDetail(shipping.getAddressDetail())
                .shippingMemo(shipping.getShippingMemo())
                .longitude(shipping.getLongitude())
                .latitude(shipping.getLatitude())
                .fee(shipping.getFee())
                .shippingStatus(ShippingStatus.READY)
                .createUser(userId)
                .build();
    }

    public Shipping save(Shipping shipping) {
        return this.shippingRepository.save(shipping);
    }
}
