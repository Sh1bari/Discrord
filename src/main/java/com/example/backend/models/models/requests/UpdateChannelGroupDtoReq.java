package com.example.backend.models.models.requests;

import com.example.backend.models.entities.ChannelGroup;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChannelGroupDtoReq {
    private String name;

    public void update(ChannelGroup c){
        c.setName(name);
    }
}
