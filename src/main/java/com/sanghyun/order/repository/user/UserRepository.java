package com.sanghyun.order.repository.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sanghyun.order.entity.user.OrderUser;

public interface UserRepository extends JpaRepository<OrderUser, Long> {

    Optional<OrderUser> findBySignId(String signId);

    Optional<OrderUser> findByUserId(String userId);
}
