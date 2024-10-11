package com.example.backend.specifications;

import com.example.backend.models.entities.Community;
import com.example.backend.models.enums.Status;
import lombok.*;
import org.springframework.data.jpa.domain.Specification;

public class CommunitySpecifications {
    public static Specification<Community> hasStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
