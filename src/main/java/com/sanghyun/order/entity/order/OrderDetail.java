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
public class OrderDetail extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column(nullable = false)
    private Long orderMasterIdx;
    private String goods;
    private Long price;
    private Long quantity;
    @Enumerated(EnumType.STRING)
    private GoodsType goodsType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderMasterIdx", referencedColumnName = "idx", insertable = false, updatable = false, nullable = false)
    private OrderMaster order;

    @Getter
    public enum GoodsType {
        UNKNOWN("알수없음"),
        BOX("박스");

        private final String goodsName;

        GoodsType(String goodsName) {
            this.goodsName = goodsName;
        }
    }

}
