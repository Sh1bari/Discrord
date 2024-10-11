package com.example.backend.models.models.requests;

import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.entities.Community;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommunityDtoReq {
    private String name;

    public void update(Community c){
        c.setName(name);
    }
}
