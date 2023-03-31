package com.sanghyun.order.entity.order;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sanghyun.order.dto.order.OrderDto.ShippingDto;
import com.sanghyun.order.entity.CommonEntity;
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
public class Shipping extends CommonEntity implements Serializable {

    @Column(unique = true, nullable = false)
    private Long orderMasterIdx;
    @Column(nullable = false)
    private String recipient;
    @Column(nullable = false)
    private String tel;
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
    private ShippingStatus shippingStatus;

    public void updateShipping(String userId, ShippingDto shippingDto) {
        this.updateUser = userId;
        this.recipient = shippingDto.getRecipient();
        this.tel = shippingDto.getTel();
        this.zonecode = shippingDto.getZonecode();
        this.roadAddress = shippingDto.getRoadAddress();
        this.jibunAddress = shippingDto.getJibunAddress();
        this.addressDetail = shippingDto.getAddressDetail();
        this.shippingMemo = shippingDto.getShippingMemo();
        this.longitude = shippingDto.getLongitude();
        this.latitude = shippingDto.getLatitude();
    }

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
