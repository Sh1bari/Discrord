package com.example.backend.repositories;

import com.example.backend.models.entities.ChannelGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelGroupRepository extends JpaRepository<ChannelGroup, Long> {
}