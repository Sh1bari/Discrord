package com.example.backend.models.models.requests;

import com.example.backend.models.entities.Community;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommunityDtoReq {
    private String name;

    public Community mapToEntity() {
        Community community = new Community();
        community.setName(name);
        return community;
    }
}
