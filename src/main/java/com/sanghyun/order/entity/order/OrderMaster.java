package com.sanghyun.order.entity.order;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sanghyun.order.entity.CommonEntity;
import com.sanghyun.order.entity.user.OrderUser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter(value = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class OrderMaster extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false)
    private String userId;
    private Long totalPrice;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false, nullable = false)
    private OrderUser order;

    @OneToMany(mappedBy = "orderMasterIdx", fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetailList;

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
