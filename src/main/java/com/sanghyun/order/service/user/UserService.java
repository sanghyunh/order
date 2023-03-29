package com.sanghyun.order.service.user;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanghyun.order.constant.Errors;
import com.sanghyun.order.dto.user.UserDto.UserJoinRequestDto;
import com.sanghyun.order.entity.user.OrderUser;
import com.sanghyun.order.exception.CommonException;
import com.sanghyun.order.persist.user.UserPersist;
import com.sanghyun.order.util.RsaUtil;
import com.sanghyun.order.util.TextUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserPersist userPersist;
    private final RsaUtil rsaUtil;
    private final TextUtil textUtil;

    @Transactional
    public void joinUser(UserJoinRequestDto userJoinRequestDto) {
        String userId = this.textUtil.createUUID();
        String password = this.rsaUtil.decrypt(userJoinRequestDto.getPassword());
        this.userPersist.save(
                this.userPersist.createUser(userId, this.textUtil.createHash(userId, password), userJoinRequestDto));
    }

    public Optional<OrderUser> find(String signId) {
        return this.userPersist.find(signId);
    }

    public OrderUser findByUserIdRequired(String userId) {
        return this.userPersist.findByUserId(userId)
                .orElseThrow(() -> new CommonException(Errors.UNKNOWN_USER));

    }
}
