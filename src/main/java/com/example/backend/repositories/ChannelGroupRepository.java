package com.example.backend.repositories;

import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.entities.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelGroupRepository extends JpaRepository<ChannelGroup, Long> {
    Page<ChannelGroup> findAll(Specification<ChannelGroup> spec, Pageable pageable);
}