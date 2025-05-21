package com.example.demo.service;

import com.example.demo.domain.column.Column;
import com.example.demo.domain.user.Pharmacist;
import com.example.demo.domain.user.User;
import com.example.demo.dto.column.ColumnRequest;
import com.example.demo.dto.column.ColumnResponse;
import com.example.demo.repository.ColumnRepository;
import com.example.demo.repository.PharmacistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {
    
    private final ColumnRepository columnRepository;
    private final PharmacistRepository pharmacistRepository;


    public void saveColumn(ColumnRequest request, User user) {
        Pharmacist pharmacist = user.getPharmacist();
        if (pharmacist == null) {
            throw new IllegalArgumentException("약사만 컬럼을 작성할 수 있습니다.");
        }

        Column column = new Column(
                request.getTitle(),
                request.getContent(),
                pharmacist
        );

        columnRepository.save(column);
    }

    public Column getColumnById(Long id) {

        return columnRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));
    }

    public List<ColumnResponse> getColumnsPerPharmacist(Long id) {
        Pharmacist pharmacist = pharmacistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 약사는 존재하지 않습니다."));

        return pharmacist.getColumns().stream()
                .map(ColumnResponse::new)
                .toList();
    }

    @Transactional
    public void updateColumn(Long id, ColumnRequest request, User user) {
        Column column = columnRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));

        if (!column.getPharmacist().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("칼럼 작성자만 수정할 수 있습니다.");
        }

        column.setTitle(request.getTitle());
        column.setContent(request.getContent());

        columnRepository.save(column);
    }

    @Transactional
    public void deleteColumn(Long id, User user) {
        Column column = columnRepository.
                findById(id).
                orElseThrow(() -> new IllegalArgumentException("존재하지 않는 칼럼입니다."));

        if (!column.getPharmacist().getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("칼럼 작성자만 삭제할 수 있습니다.");
        }

        columnRepository.deleteById(id);
    }

    public List<ColumnResponse> getAllColumns() {
        List<Column> columns = columnRepository.findAll();

        return columns.stream()
                .map(ColumnResponse::new)
                .toList();
    }
}
