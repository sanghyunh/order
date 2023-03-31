package com.sanghyun.order.controller.order;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanghyun.order.dto.order.OrderDto.OrderFindResponseDto;
import com.sanghyun.order.dto.order.OrderDto.OrderPostRequestDto;
import com.sanghyun.order.dto.order.OrderDto.OrderPutRequestDto;
import com.sanghyun.order.service.order.OrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/v1")
    public ResponseEntity<Void> order(@RequestBody OrderPostRequestDto orderPostRequestDto,
            @RequestAttribute String userId) {

        this.orderService.order(orderPostRequestDto, userId);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/v1")
    public ResponseEntity<Void> putOrder(@RequestBody OrderPutRequestDto orderPutRequestDto,
            @RequestAttribute String userId) {

        this.orderService.putOrder(orderPutRequestDto, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/v1/{startDate}/{endDate}")
    public ResponseEntity<OrderFindResponseDto> findOrder(@PathVariable String startDate,
            @PathVariable String endDate,
            @RequestAttribute String userId) {

        OrderFindResponseDto orderFindResponseDto = this.orderService.findOrder(startDate, endDate, userId);

        return new ResponseEntity<>(orderFindResponseDto, HttpStatus.OK);
    }

}
