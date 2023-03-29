package com.sanghyun.order.dto.order;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sanghyun.order.entity.order.OrderDetail.GoodsType;
import com.sanghyun.order.entity.order.Shipping.ShippingStatus;
import lombok.Data;

public class OrderDto {

    @Data
    public static class OrderRequestDto {
        @NotNull
        private List<GoodsDto> goodsList;
        @NotNull
        private ShippingDto shipping;
    }

    @Data
    public static class GoodsDto {
        @NotEmpty
        private String goods;
        @Min(1)
        private Long quantity;
        @Min(1)
        private Long price;
        @NotNull
        private GoodsType goodsType;
    }
    @Data
    public static class ShippingDto {
        @NotEmpty
        private String recipient;
        @NotEmpty
        private String tel;
        @NotEmpty
        private String zonecode;
        @NotEmpty
        private String roadAddress;
        @NotEmpty
        private String jibunAddress;
        @NotEmpty
        private String addressDetail;
        @NotEmpty
        private String shippingMemo;
        @Min(1)
        private Double longitude;
        @Min(1)
        private Double latitude;
    }

}
