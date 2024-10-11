package com.example.backend.models.models.requests;

import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.ChannelGroup;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChannelDtoReq {
    private String name;
    private Long channelGroupId;

    public void update(Channel c){
        c.setName(name);
    }
}
