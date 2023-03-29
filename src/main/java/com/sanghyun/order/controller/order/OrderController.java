package com.sanghyun.order.controller.order;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanghyun.order.dto.order.OrderDto.OrderRequestDto;
import com.sanghyun.order.service.order.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/v1")
    public ResponseEntity<Void> order(@RequestBody OrderRequestDto orderRequestDto,
            @RequestAttribute String userId,
            HttpServletRequest httpServletRequest) {

        this.orderService.order(orderRequestDto, userId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
