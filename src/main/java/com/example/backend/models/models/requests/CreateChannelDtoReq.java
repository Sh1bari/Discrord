package com.example.backend.models.models.requests;

import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.enums.ChannelType;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelDtoReq {
    private String name;
    private ChannelType channelType;
    private Long channelGroupId;

    public Channel mapToEntity(){
        Channel c = new Channel();
        c.setName(name);
        c.setChannelType(channelType);
        return c;
    }
}
