package com.example.backend.models.entities;

import com.example.backend.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "create_time", updatable = false)
    private Instant createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Instant updateTime;

    /*@CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_by")
    private User modifiedBy;*/

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    public OffsetDateTime getCreateTime() {
        return createTime != null ? OffsetDateTime.ofInstant(createTime, ZoneOffset.UTC) : null;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime != null ? OffsetDateTime.ofInstant(updateTime, ZoneOffset.UTC) : null;
    }
}
