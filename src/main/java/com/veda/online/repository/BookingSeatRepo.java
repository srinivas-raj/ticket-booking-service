package com.veda.online.repository;

import com.veda.online.entity.SeatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingSeatRepo extends JpaRepository<
        SeatEntity, Long> {
    List<SeatEntity> findByParentId(Long id);
}
