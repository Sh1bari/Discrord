package com.example.backend.models.models.dtos;

import com.example.backend.models.entities.Community;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunityDto extends BaseEntityDto{
    private Long id;
    private String name;

    private static CommunityDtoBuilder basicMapping(Community c){
        return CommunityDto.builder()
                .id(c.getId())
                .name(c.getName());
    }

    public static CommunityDto mapFromEntity(Community c){
        CommunityDto d = basicMapping(c).build();
        d.map(c);
        return d;
    }

    public static CommunityDto mapFromEntitySimplified(Community c){
        CommunityDto d = basicMapping(c).build();
        d.map(c);
        return d;
    }
}
