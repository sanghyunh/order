package com.sanghyun.order.entity.order;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sanghyun.order.dto.order.OrderDto.ShippingDto;
import com.sanghyun.order.entity.CommonEntity;
import com.sanghyun.order.entity.user.OrderUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "OrderMaster", indexes = {
        @Index(name = "orderDate", columnList = "orderDate"),
        @Index(name = "userId", columnList = "userId")
})
public class OrderMaster extends CommonEntity {

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private Long totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(nullable = false)
    private String orderDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false, nullable = false)
    private OrderUser orderUser;

    @OneToMany(mappedBy = "orderMasterIdx", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetailList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idx", referencedColumnName = "orderMasterIdx",insertable = false, updatable = false, nullable = false)
    private Shipping shipping;

    @Getter
    public enum OrderStatus {
        ORDER("주문"),
        COMPLETE("주문완료"),
        CANCEL("취소");

        private final String message;

        OrderStatus(String message) {
            this.message = message;
        }
    }

}
