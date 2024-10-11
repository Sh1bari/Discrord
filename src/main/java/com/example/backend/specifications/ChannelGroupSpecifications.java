package com.example.backend.specifications;

import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class ChannelGroupSpecifications {
    public static Specification<ChannelGroup> hasStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }

    public static Specification<ChannelGroup> hasCommunityId(Long comId) {
        return (root, query, criteriaBuilder) -> {
            if (comId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("community").get("id"), comId);
        };
    }
}
