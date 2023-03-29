package com.sanghyun.order.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanghyun.order.entity.order.Shipping;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
