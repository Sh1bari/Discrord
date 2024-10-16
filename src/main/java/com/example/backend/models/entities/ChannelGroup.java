package com.example.backend.models.entities;

import com.example.backend.models.enums.ChannelGroupType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "channel_groups")
public class ChannelGroup extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @Enumerated(EnumType.STRING)
    private ChannelGroupType channelGroupType = ChannelGroupType.CUSTOM;

    @OneToMany(mappedBy = "channelGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Channel> channels = new ArrayList<>();

}
