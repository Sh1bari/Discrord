package com.example.backend.models.entities;

import com.example.backend.models.enums.ChannelType;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "channels")
public class Channel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ChannelType channelType;

    @ManyToOne
    @JoinColumn(name = "channel_group_id")
    private ChannelGroup channelGroup;

}
