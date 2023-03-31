package com.sanghyun.order.repository.order;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sanghyun.order.entity.order.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

    @Query(value = "SELECT A  FROM OrderMaster A "
            + "JOIN FETCH A.orderUser B "
            + "JOIN FETCH A.orderDetailList C "
            + "JOIN FETCH A.shipping D "
            + "WHERE A.orderDate BETWEEN :startDate AND :endDate  "
            + "AND A.userId = :userId")
    List<OrderMaster> findByUserIdAndOrderDateBetween(
            @Param("userId") String userId,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate);
}
