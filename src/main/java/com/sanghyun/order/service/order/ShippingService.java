package com.sanghyun.order.service.order;

import org.springframework.stereotype.Service;

import com.sanghyun.order.persist.order.ShippingPersist;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingService {

    private final ShippingPersist shippingPersist;

}
