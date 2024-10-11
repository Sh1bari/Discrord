package com.example.backend.controllers;

import com.example.backend.models.entities.ChannelGroup;
import com.example.backend.models.enums.Status;
import com.example.backend.models.models.dtos.ChannelGroupDto;
import com.example.backend.models.models.requests.CreateChannelGroupDtoReq;
import com.example.backend.models.models.requests.UpdateChannelGroupDtoReq;
import com.example.backend.services.ChannelGroupService;
import com.example.backend.specifications.ChannelGroupSpecifications;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Channel group API", description = "")
public class ChannelGroupController {
    private final ChannelGroupService channelGroupService;

    @Operation(summary = "Создать группу каналов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/channel-groups")
    public ResponseEntity<ChannelGroupDto> create(
            @RequestBody CreateChannelGroupDtoReq req) {
        ChannelGroupDto res = ChannelGroupDto.mapFromEntity(
                channelGroupService.create(req));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Получить группы каналов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/channel-groups")
    public ResponseEntity<Page<ChannelGroupDto>> findAll(
            @RequestParam(required = false, defaultValue = "ACTIVE") Status status,
            @RequestParam(required = false) Long communityId,
            @PageableDefault Pageable pageable) {
        Specification<ChannelGroup> spec = Specification.where(ChannelGroupSpecifications.hasStatus(status))
                .and(ChannelGroupSpecifications.hasCommunityId(communityId));
        Page<ChannelGroupDto> res = channelGroupService.findAll(spec, pageable)
                .map(ChannelGroupDto::mapFromEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Найти группу каналов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/channel-groups/{id}")
    public ResponseEntity<ChannelGroupDto> findById(@PathVariable Long id) {
        ChannelGroupDto res = ChannelGroupDto.mapFromEntity(
                channelGroupService.findById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }
    @Operation(summary = "Обновить группу каналов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PutMapping("/channel-groups/{id}")
    public ResponseEntity<ChannelGroupDto> update(@PathVariable Long id,
                                               @RequestBody UpdateChannelGroupDtoReq req) {
        ChannelGroupDto res = ChannelGroupDto.mapFromEntity(
                channelGroupService.update(id, req));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }
    @Operation(summary = "Удалить группу каналов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @DeleteMapping("/channel-groups/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        channelGroupService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
