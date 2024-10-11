package com.example.backend.models.models.dtos;

import com.example.backend.models.entities.BaseEntity;
import com.example.backend.models.enums.Status;
import com.example.backend.utils.Formatter;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityDto {
    private String createTime;
    private String updateTime;
    private Status status;

    public <T extends BaseEntity> void map(T t){
        this.setCreateTime(t.getCreateTime() != null ? t.getCreateTime().format(Formatter.formatter) : null);
        this.setUpdateTime(t.getUpdateTime() != null ? t.getUpdateTime().format(Formatter.formatter) : null);
        this.setStatus(t.getStatus());
    }
}
