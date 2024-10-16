package com.example.backend.controllers;

import com.example.backend.models.entities.Channel;
import com.example.backend.models.enums.Status;
import com.example.backend.models.models.dtos.ChannelDto;
import com.example.backend.models.models.requests.CreateChannelDtoReq;
import com.example.backend.models.models.requests.UpdateChannelDtoReq;
import com.example.backend.services.ChannelService;
import com.example.backend.specifications.ChannelSpecifications;
import dev.onvoid.webrtc.PeerConnectionFactory;
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
@Tag(name = "Channel API", description = "")
public class ChannelController {
    private final ChannelService channelService;
    @Operation(summary = "Создать канал")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/channels")
    public ResponseEntity<ChannelDto> create(
            @RequestBody CreateChannelDtoReq req) {
        ChannelDto res = ChannelDto.mapFromEntity(
                channelService.create(req));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Получить канал")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/channels")
    public ResponseEntity<Page<ChannelDto>> findAll(
            @RequestParam(required = false, defaultValue = "ACTIVE") Status status,
            @RequestParam(required = false) Long channelGroupId,
            @PageableDefault Pageable pageable) {
        Specification<Channel> spec = Specification.where(ChannelSpecifications.hasStatus(status))
                .and(ChannelSpecifications.hasChannelGroupId(channelGroupId));
        Page<ChannelDto> res = channelService.findAll(spec, pageable)
                .map(ChannelDto::mapFromEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Найти канал по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/channels/{id}")
    public ResponseEntity<ChannelDto> findById(@PathVariable Long id) {
        ChannelDto res = ChannelDto.mapFromEntity(
                channelService.findById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Обновить канал по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PutMapping("/channels/{id}")
    public ResponseEntity<ChannelDto> update(@PathVariable Long id,
                                                  @RequestBody UpdateChannelDtoReq req) {
        ChannelDto res = ChannelDto.mapFromEntity(
                channelService.update(id, req));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Удалить канал по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @DeleteMapping("/channels/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        channelService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
