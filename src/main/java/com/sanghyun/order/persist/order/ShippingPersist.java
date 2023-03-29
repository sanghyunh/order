package com.sanghyun.order.persist.order;

import org.springframework.stereotype.Service;

import com.sanghyun.order.repository.order.ShippingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShippingPersist {

    private final ShippingRepository shippingRepository;

}
