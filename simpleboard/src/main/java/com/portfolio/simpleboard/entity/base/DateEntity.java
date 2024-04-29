package com.portfolio.simpleboard.entity.base;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
@ToString
public abstract class DateEntity implements Serializable {

    @CreatedDate
    private LocalDateTime cDate;
    @LastModifiedDate
    private LocalDateTime mDate;
}
