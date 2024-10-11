package com.example.backend.services;

import com.example.backend.exceptions.GeneralException;
import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.entities.Community;
import com.example.backend.models.models.requests.CreateChannelGroupDtoReq;
import com.example.backend.models.models.requests.CreateCommunityDtoReq;
import com.example.backend.models.models.requests.UpdateChannelGroupDtoReq;
import com.example.backend.repositories.ChannelGroupRepository;
import com.example.backend.utils.BaseEntityUtil;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelGroupService {
    private final ChannelGroupRepository channelGroupRepo;
    private final CommunityService communityService;
    @Transactional(readOnly = true)
    public ChannelGroup findById(Long id) {
        return channelGroupRepo.findById(id)
                .orElseThrow(()->new GeneralException(404, "Channel group not found"));
    }
    @Transactional
    public ChannelGroup save(ChannelGroup channelGroup) {
        return channelGroupRepo.save(channelGroup);
    }
    @Transactional
    public ChannelGroup delete(Long id) {
        ChannelGroup c = findById(id);
        BaseEntityUtil.delete(c);
        return c;
    }
    @Transactional
    public ChannelGroup update(Long id, UpdateChannelGroupDtoReq req) {
        ChannelGroup c = findById(id);
        req.update(c);
        return save(c);
    }
    @Transactional
    public Page<ChannelGroup> findAll(Specification<ChannelGroup> spec, Pageable pageable) {
        return channelGroupRepo.findAll(spec, pageable);
    }

    @Transactional
    public ChannelGroup create(CreateChannelGroupDtoReq req) {
        ChannelGroup c = req.mapToEntity();
        c.setCommunity(communityService.findById(req.getCommunityId()));
        return save(c);
    }
}
