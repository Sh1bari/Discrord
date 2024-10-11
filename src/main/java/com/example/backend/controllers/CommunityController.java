package com.example.backend.controllers;

import com.example.backend.models.entities.Community;
import com.example.backend.models.models.dtos.CommunityDto;
import com.example.backend.models.models.requests.CreateCommunityDtoReq;
import com.example.backend.models.models.requests.UpdateCommunityDtoReq;
import com.example.backend.services.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Community API", description = "")
public class CommunityController {
    private final CommunityService communityService;
    @Operation(summary = "Создать сообщество")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/communities")
    public ResponseEntity<CommunityDto> create(
            @RequestBody CreateCommunityDtoReq req) {
        CommunityDto res = CommunityDto.mapFromEntity(
                communityService.create(req));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Получить сообщества")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @GetMapping("/communities")
    public ResponseEntity<Page<CommunityDto>> findAll(
            @PageableDefault Pageable pageable) {
        Specification<Community> spec = Specification.where(null);
        Page<CommunityDto> res = communityService.findAll(spec, pageable)
                .map(CommunityDto::mapFromEntity);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Найти сообщество по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PostMapping("/communities/{id}")
    public ResponseEntity<CommunityDto> findById(@PathVariable Long id) {
        CommunityDto res = CommunityDto.mapFromEntity(
                communityService.findById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Обновить сообщество по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @PutMapping("/communities/{id}")
    public ResponseEntity<CommunityDto> update(@PathVariable Long id,
                                               @RequestBody UpdateCommunityDtoReq req) {
        CommunityDto res = CommunityDto.mapFromEntity(
                communityService.update(id, req));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Удалить сообщество по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success")
    })
    @DeleteMapping("/communities/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        communityService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
