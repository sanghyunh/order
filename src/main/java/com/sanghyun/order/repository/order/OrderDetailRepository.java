package com.sanghyun.order.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanghyun.order.entity.order.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
