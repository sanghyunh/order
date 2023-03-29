package com.sanghyun.order.entity.order;

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

import com.sanghyun.order.entity.CommonEntity;
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
public class Shipping extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(unique = true, nullable = false)
    private Long orderIdx;
    @Column(nullable = false)
    private String recipient;
    @Column(nullable = false)
    private String tel;
    @Column(nullable = false)
    private String userSelectedType;
    @Column(nullable = false)
    private String zonecode;
    @Column(nullable = false)
    private String roadAddress;
    @Column(nullable = false)
    private String jibunAddress;
    @Column(nullable = false)
    private String addressDetail;
    private String shippingMemo;
    private String trackingNumber;
    @Column(nullable = false)
    private Double longitude;
    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Integer fee;
    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderIdx", referencedColumnName = "idx", insertable = false, updatable = false, nullable = false)
    private OrderMaster order;

    @Getter
    public enum ShippingStatus {
        READY("미입력"),
        ONGOING("배송중"),
        COMPLETE("배송완료");

        private final String name;

        ShippingStatus(String name) {
            this.name = name;
        }
    }

}
