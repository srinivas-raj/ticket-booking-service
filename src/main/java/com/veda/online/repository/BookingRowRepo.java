package com.veda.online.repository;

import com.veda.online.entity.BookingTypeDetails;
import com.veda.online.entity.RowEntity;
import com.veda.online.model.RowDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRowRepo extends JpaRepository<
        RowEntity, Long> {
    List<RowEntity> findByParentId(Long id);
}
