package com.sanghyun.order.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.order.OrderDto.GoodsDto;
import com.sanghyun.order.dto.order.OrderDto.OrderRequestDto;
import com.sanghyun.order.dto.order.OrderDto.ShippingDto;
import com.sanghyun.order.dto.user.UserDto.UserBaseDto;
import com.sanghyun.order.entity.order.Shipping.ShippingStatus;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends CommonTest {

    @Test
    @Transactional
    public void orderTest() throws Exception {

        UserBaseDto requestDto = this.getLoginDto();
        String requestJson = this.converterUtil.toJsonString(requestDto);

        ResultActions resultAction = this.mockMvc.perform(post("/auth/v1/token")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());
        TokenDto tokenDto = this.parseToken(resultAction);

        OrderRequestDto orderRequestDto = new OrderRequestDto();
        ShippingDto shipping = new ShippingDto();
        shipping.setTel("1");
        shipping.setRecipient("test");
        shipping.setAddressDetail("AddressDetail");
        shipping.setJibunAddress("JibunAddress");
        shipping.setRoadAddress("RoadAddress");
        shipping.setShippingMemo("ShippingMemo");
        shipping.setLongitude(37.48474214471537);
        shipping.setLatitude(126.93004568732549);
        orderRequestDto.setShipping(shipping);
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setGoods("상품1");
        goodsDto.setPrice(5000L);
        goodsDto.setQuantity(10L);
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        goodsDtoList.add(goodsDto);
        orderRequestDto.setGoodsList(goodsDtoList);
        this.mockMvc.perform(post("/order/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", tokenDto.getToken())
                        .content(this.converterUtil.toJsonString(orderRequestDto))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

}
