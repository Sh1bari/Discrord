package com.example.backend.models.models.dtos;

import com.example.backend.models.entities.BaseEntity;
import com.example.backend.utils.Formatter;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntityDto {
    private String createTime;
    private String updateTime;

    public <T extends BaseEntity> void map(T t){
        this.setCreateTime(t.getCreateTime() != null ? t.getCreateTime().format(Formatter.formatter) : null);
        this.setUpdateTime(t.getUpdateTime() != null ? t.getUpdateTime().format(Formatter.formatter) : null);
    }
}
