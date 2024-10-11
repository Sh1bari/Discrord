package com.example.backend.services;

import com.example.backend.exceptions.GeneralException;
import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.entities.Community;
import com.example.backend.models.enums.ChannelGroupType;
import com.example.backend.models.models.requests.CreateCommunityDtoReq;
import com.example.backend.models.models.requests.UpdateChannelGroupDtoReq;
import com.example.backend.models.models.requests.UpdateCommunityDtoReq;
import com.example.backend.repositories.CommunityRepository;
import com.example.backend.utils.BaseEntityUtil;
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
    public Community delete(Long id) {
        Community c = findById(id);
        BaseEntityUtil.delete(c);
        return c;
    }

    @Transactional
    public Community update(Long id, UpdateCommunityDtoReq req) {
        Community c = findById(id);
        req.update(c);
        return save(c);
    }
    @Transactional
    public Community create(CreateCommunityDtoReq req) {
        Community com = req.mapToEntity();
        ChannelGroup cg = new ChannelGroup();
        cg.setChannelGroupType(ChannelGroupType.SYSTEM);
        com.getChannelGroups().add(cg);
        return save(com);
    }

}
