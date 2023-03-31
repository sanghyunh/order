package com.sanghyun.order.dto.order;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sanghyun.order.entity.order.OrderDetail;
import com.sanghyun.order.entity.order.OrderDetail.GoodsType;
import com.sanghyun.order.entity.order.OrderMaster;
import com.sanghyun.order.entity.order.OrderMaster.OrderStatus;
import com.sanghyun.order.entity.order.Shipping;
import com.sanghyun.order.entity.order.Shipping.ShippingStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

public class OrderDto {

    @Data
    public static class OrderPostRequestDto {

        @NotNull
        private List<GoodsDto> goodsList;
        @NotNull
        private ShippingDto shipping;
    }

    @Data
    public static class OrderPutRequestDto {
        @NotEmpty
        @Min(1)
        private Long orderIdx;
        @NotNull
        private ShippingDto shippingDto;
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
        private Integer fee;
    }

    @Builder
    @Getter
    public static class OrderFindResponseDto {

        private List<OrderResponseDto> orderList;

        public static OrderFindResponseDto of(List<OrderMaster> orderMaster) {
            return OrderFindResponseDto.builder()
                    .orderList(orderMaster.stream()
                            .map(OrderResponseDto::of)
                            .collect(Collectors.toList()))
                    .build();
        }

        @Builder
        @Getter
        public static class OrderResponseDto {

            private Long orderIdx;
            private String orderDate;
            private OrderStatus orderStatus;
            private List<GoodsResponseDto> goodsList;
            private ShippingResponseDto shipping;

            public static OrderResponseDto of(OrderMaster orderMaster) {
                return OrderResponseDto.builder()
                        .orderIdx(orderMaster.getIdx())
                        .orderDate(orderMaster.getOrderDate())
                        .orderStatus(orderMaster.getOrderStatus())
                        .goodsList(orderMaster.getOrderDetailList()
                                .stream()
                                .map(GoodsResponseDto::of)
                                .collect(Collectors.toList()))
                        .shipping(ShippingResponseDto.of(orderMaster.getShipping()))
                        .build();
            }
        }

        @Builder
        @Getter
        public static class GoodsResponseDto {

            private String goods;
            private Long quantity;
            private Long price;
            private GoodsType goodsType;

            public static GoodsResponseDto of(OrderDetail orderDetail) {
                return GoodsResponseDto.builder()
                        .goods(orderDetail.getGoods())
                        .quantity(orderDetail.getQuantity())
                        .price(orderDetail.getPrice())
                        .goodsType(orderDetail.getGoodsType())
                        .build();
            }
        }

        @Builder
        @Getter
        public static class ShippingResponseDto {

            private String recipient;
            private String tel;
            private String zonecode;
            private String roadAddress;
            private String jibunAddress;
            private String addressDetail;
            private String shippingMemo;
            private Double longitude;
            private Double latitude;
            private Integer fee;
            private ShippingStatus shippingStatus;

            public static ShippingResponseDto of(Shipping shipping) {
                return ShippingResponseDto.builder()
                        .recipient(shipping.getRecipient())
                        .tel(shipping.getTel())
                        .zonecode(shipping.getZonecode())
                        .roadAddress(shipping.getRoadAddress())
                        .jibunAddress(shipping.getJibunAddress())
                        .addressDetail(shipping.getAddressDetail())
                        .shippingMemo(shipping.getShippingMemo())
                        .longitude(shipping.getLongitude())
                        .latitude(shipping.getLatitude())
                        .fee(shipping.getFee())
                        .shippingStatus(shipping.getShippingStatus())
                        .build();
            }
        }
    }

}
