package com.sanghyun.order.persist.user;

import java.util.UUID;
import org.springframework.stereotype.Service;

import com.sanghyun.order.dto.user.UserDto.UserJoinRequestDto;
import com.sanghyun.order.entity.user.User;
import com.sanghyun.order.repository.user.UserRepository;
import com.sanghyun.order.util.TextUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserPersist {

    private final UserRepository userRepository;
    private final TextUtil textUtil;

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User createUser(UserJoinRequestDto userJoinRequestDto) {
        String userId = UUID.randomUUID().toString().toUpperCase();
        return User.builder()
                .userId(userId)
                .signId(userJoinRequestDto.getId())
                .name(userJoinRequestDto.getName())
                .password(this.textUtil.createHash(userId, userJoinRequestDto.getPassword()))
                .build();
    }

}
