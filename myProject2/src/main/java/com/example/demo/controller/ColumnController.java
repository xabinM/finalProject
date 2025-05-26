package com.example.demo.controller;

import com.example.demo.domain.user.User;
import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.column.*;
import com.example.demo.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/columns")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    // 💊 칼럼 등록
    @PostMapping
    public ResponseEntity<?> registerColumn(@RequestBody ColumnRequest request,
                                       @AuthenticationPrincipal User user) {
        try {
            columnService.saveColumn(request, user);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // ✍ 칼럼 상세
    @GetMapping("/{id}")
    public ResponseEntity<?> getColumnInfo(@PathVariable Long id) {
        try {
            ColumnDetailDto columnDetail = columnService.getColumnDetail(id);

            return ResponseEntity
                    .ok()
                    .body(new ColumnDetailResponse(columnDetail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // 📋 약사별 칼럼 목록 조회
    @GetMapping("/pharmacists/{id}")
    public ResponseEntity<?> getColumnsPerPharmacist(@PathVariable Long id) {
        try {
            List<ColumnDto> columns = columnService.getColumnsPerPharmacist(id);

            return ResponseEntity.ok().body(new ColumnListPerPharmacistResponse(columns));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> editColumn(@PathVariable Long id,
                                       @RequestBody ColumnRequest request,
                                       @AuthenticationPrincipal User user) {
        try {
            columnService.updateColumn(id, request, user);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeColumn(@PathVariable Long id,
                                       @AuthenticationPrincipal User user) {
        try {
            columnService.deleteColumn(id, user);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    // 전체 칼럼 리스트 보기
    @GetMapping("/all")
    public ResponseEntity<?> getAllColumns() {
        try {
            List<ColumnDto> columns = columnService.getAllColumns();

            return ResponseEntity.ok().body(new ColumnAllListResponse(columns));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

}
