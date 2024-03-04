package com.example.thuctap.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;


@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class TimeAuditable implements Serializable {
    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(updatable = false, columnDefinition = "timestamp without time zone")
    private LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.of("+07:00"));

    @LastModifiedDate
    @Column(columnDefinition = "timestamp without time zone")
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneOffset.of("+07:00"));
}
