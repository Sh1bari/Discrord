package com.example.backend.services;

import com.example.backend.exceptions.GeneralException;
import com.example.backend.models.entities.Channel;
import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.models.requests.CreateChannelDtoReq;
import com.example.backend.models.models.requests.CreateChannelGroupDtoReq;
import com.example.backend.models.models.requests.UpdateChannelDtoReq;
import com.example.backend.models.models.requests.UpdateChannelGroupDtoReq;
import com.example.backend.repositories.ChannelRepository;
import com.example.backend.utils.BaseEntityUtil;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChannelService {
    private final ChannelRepository channelRepo;
    private final ChannelGroupService channelGroupService;

    @Transactional(readOnly = true)
    public Channel findById(Long id) {
        return channelRepo.findById(id)
                .orElseThrow(()->new GeneralException(404, "Channel not found"));
    }
    @Transactional
    public Channel save(Channel channel) {
        return channelRepo.save(channel);
    }
    @Transactional
    public Page<Channel> findAll(Specification<Channel> spec, Pageable pageable) {
        return channelRepo.findAll(spec, pageable);
    }
    @Transactional
    public Channel delete(Long id) {
        Channel c = findById(id);
        BaseEntityUtil.delete(c);
        return c;
    }
    @Transactional
    public Channel update(Long id, UpdateChannelDtoReq req) {
        Channel c = findById(id);
        req.update(c);
        if(!c.getChannelGroup().getId().equals(req.getChannelGroupId())){
            c.setChannelGroup(channelGroupService.findById(req.getChannelGroupId()));
        }
        return save(c);
    }
    @Transactional
    public Channel create(CreateChannelDtoReq req) {
        Channel c = req.mapToEntity();
        c.setChannelGroup(channelGroupService.findById(req.getChannelGroupId()));
        return save(c);
    }
}
