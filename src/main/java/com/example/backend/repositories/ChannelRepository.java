package com.example.backend.repositories;

import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.Community;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
    Page<Channel> findAll(Specification<Channel> spec, Pageable pageable);
}