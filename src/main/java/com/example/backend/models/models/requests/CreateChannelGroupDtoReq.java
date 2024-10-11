package com.example.backend.models.models.requests;

import com.example.backend.models.entities.ChannelGroup;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateChannelGroupDtoReq {
    private String name;
    private Long communityId;

    public ChannelGroup mapToEntity(){
        ChannelGroup c = new ChannelGroup();
        c.setName(name);
        return c;
    }
}
