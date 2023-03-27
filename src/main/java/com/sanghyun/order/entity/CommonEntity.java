package com.sanghyun.order.entity;

import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.MappedSuperclass;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@SuperBuilder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public abstract class CommonEntity {

    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createDate;
    private Long createUser;
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updateDate;
    private Long updateUser;

}

