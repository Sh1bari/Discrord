package com.example.backend.specifications;

import com.example.backend.models.entities.Channel;
import com.example.backend.models.enums.Status;
import org.springframework.data.jpa.domain.Specification;

public class ChannelSpecifications {
    public static Specification<Channel> hasChannelGroupId(Long cId) {
        return (root, query, criteriaBuilder) -> {
            if (cId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("channelGroup").get("id"), cId);
        };
    }

    public static Specification<Channel> hasStatus(Status status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
