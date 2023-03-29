package com.sanghyun.order.persist.user;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.sanghyun.order.dto.user.UserDto.UserJoinRequestDto;
import com.sanghyun.order.entity.user.OrderUser;
import com.sanghyun.order.repository.user.UserRepository;
import com.sanghyun.order.util.RsaUtil;
import com.sanghyun.order.util.TextUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPersist {

    private final UserRepository userRepository;
    private final TextUtil textUtil;
    private final RsaUtil rsaUtil;

    public OrderUser save(OrderUser orderUser) {
        return this.userRepository.save(orderUser);
    }

    public OrderUser createUser(String userId, String password, UserJoinRequestDto userJoinRequestDto) {
        return OrderUser.builder()
                .userId(userId)
                .signId(userJoinRequestDto.getSignId())
                .name(userJoinRequestDto.getName())
                .password(password)
                .createUser(userId)
                .build();
    }

    public Optional<OrderUser> find(String signId) {
        return this.userRepository.findBySignId(signId);
    }

    public Optional<OrderUser> findByUserId(String userId) {
        return this.userRepository.findByUserId(userId);
    }
}
