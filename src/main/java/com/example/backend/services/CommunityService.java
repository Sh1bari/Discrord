package com.example.backend.services;

import com.example.backend.exceptions.GeneralException;
import com.example.backend.models.entities.Community;
import com.example.backend.models.models.requests.CreateCommunityDtoReq;
import com.example.backend.repositories.CommunityRepository;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final CommunityRepository communityRepo;

    @Transactional(readOnly = true)
    public Community findById(Long id) {
        return communityRepo.findById(id)
                .orElseThrow(()->new GeneralException(404, "Community not found"));
    }
    @Transactional
    public Community save(Community community) {
        return communityRepo.save(community);
    }
    @Transactional
    public Page<Community> findAll(Specification<Community> spec, Pageable pageable) {
        return communityRepo.findAll(spec, pageable);
    }

    @Transactional
    public Community create(CreateCommunityDtoReq req) {
        Community com = req.mapToEntity();
        return communityRepo.save(com);
    }

}
