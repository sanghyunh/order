package com.sanghyun.order.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.CommonTest;
import com.sanghyun.order.dto.auth.AuthDto.TokenDto;
import com.sanghyun.order.dto.order.OrderDto.GoodsDto;
import com.sanghyun.order.dto.order.OrderDto.OrderPostRequestDto;
import com.sanghyun.order.dto.order.OrderDto.ShippingDto;
import com.sanghyun.order.entity.order.OrderDetail.GoodsType;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends CommonTest {

    @Test
    public void orderTest() throws Exception {
        TokenDto tokenDto = this.login();

        OrderPostRequestDto orderPostRequestDto = new OrderPostRequestDto();
        ShippingDto shipping = new ShippingDto();
        shipping.setTel("1");
        shipping.setRecipient("test");
        shipping.setAddressDetail("AddressDetail");
        shipping.setJibunAddress("JibunAddress");
        shipping.setRoadAddress("RoadAddress");
        shipping.setShippingMemo("ShippingMemo");
        shipping.setZonecode("12346");
        shipping.setLongitude(37.48474214471537);
        shipping.setLatitude(126.93004568732549);
        shipping.setFee(5000);
        orderPostRequestDto.setShipping(shipping);
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setGoods("상품1");
        int start = 1000;
        int end = 5000;
        double range = end - start + 1;
        goodsDto.setPrice((long) (new Random().nextDouble() * range + start));
        start = 1;
        end = 10;
        range = end - start + 1;
        goodsDto.setQuantity((long) (new Random().nextDouble() * range + start));
        goodsDto.setGoodsType(GoodsType.BOX);
        List<GoodsDto> goodsDtoList = new ArrayList<>();
        goodsDtoList.add(goodsDto);
        orderPostRequestDto.setGoodsList(goodsDtoList);
        this.mockMvc.perform(post("/order/v1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", tokenDto.getToken())
                        .content(this.converterUtil.toJsonString(orderPostRequestDto))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    @Transactional
    public void findOrderTest() throws Exception {
        TokenDto tokenDto = this.login();
        this.mockMvc.perform(get("/order/v1/20230330/20230331")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", tokenDto.getToken())
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

}
