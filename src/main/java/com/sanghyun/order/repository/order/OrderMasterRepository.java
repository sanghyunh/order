package com.sanghyun.order.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanghyun.order.entity.order.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

}
