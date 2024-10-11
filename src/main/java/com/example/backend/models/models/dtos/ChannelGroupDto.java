package com.example.backend.models.models.dtos;

import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.entities.Community;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelGroupDto extends BaseEntityDto{
    private Long id;
    private String name;
    private Long communityId;

    private static ChannelGroupDto.ChannelGroupDtoBuilder basicMapping(ChannelGroup c){
        return ChannelGroupDto.builder()
                .id(c.getId())
                .name(c.getName())
                .communityId(c.getCommunity().getId());
    }

    public static ChannelGroupDto mapFromEntity(ChannelGroup c){
        ChannelGroupDto d = basicMapping(c).build();
        d.map(c);
        return d;
    }

    public static ChannelGroupDto mapFromEntitySimplified(ChannelGroup c){
        ChannelGroupDto d = basicMapping(c).build();
        d.map(c);
        return d;
    }
}
