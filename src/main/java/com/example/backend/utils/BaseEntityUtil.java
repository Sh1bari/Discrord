package com.example.backend.utils;

import com.example.backend.models.entities.BaseEntity;
import com.example.backend.models.enums.Status;

public class BaseEntityUtil {
    public static <T extends BaseEntity> void delete(T entity) {
        entity.setStatus(Status.DELETED);
    }
}
