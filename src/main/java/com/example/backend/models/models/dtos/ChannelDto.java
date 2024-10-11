package com.example.backend.models.models.dtos;

import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.enums.ChannelType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDto extends BaseEntityDto{
    private Long id;
    private String name;
    private ChannelType channelType;
    private Long channelGroupId;

    private static ChannelDto.ChannelDtoBuilder basicMapping(Channel c){
        return ChannelDto.builder()
                .id(c.getId())
                .name(c.getName())
                .channelType(c.getChannelType())
                .channelGroupId(c.getChannelGroup().getId());
    }

    public static ChannelDto mapFromEntity(Channel c){
        ChannelDto d = basicMapping(c).build();
        d.map(c);
        return d;
    }

    public static ChannelDto mapFromEntitySimplified(Channel c){
        ChannelDto d = basicMapping(c).build();
        d.map(c);
        return d;
    }
}
