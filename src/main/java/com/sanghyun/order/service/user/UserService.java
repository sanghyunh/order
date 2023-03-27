package com.sanghyun.order.service.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.dto.user.UserDto.UserJoinRequestDto;
import com.sanghyun.order.persist.user.UserPersist;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPersist userPersist;

    @Transactional
    public void joinUser(UserJoinRequestDto userJoinRequestDto) {
        this.userPersist.save(this.userPersist.createUser(userJoinRequestDto));
    }

}
