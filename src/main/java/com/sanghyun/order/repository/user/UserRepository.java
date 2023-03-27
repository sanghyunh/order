package com.sanghyun.order.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanghyun.order.entity.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
