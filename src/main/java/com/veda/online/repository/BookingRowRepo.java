package com.veda.online.repository;

import com.veda.online.entity.RowEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingRowRepo {
    List<RowEntity> findByParentId(Long id);

    RowEntity save(RowEntity rowEntity);
}
