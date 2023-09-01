package com.veda.online.repository;

import com.veda.online.entity.SeatEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BookingSeatRepo{
    List<SeatEntity> findByParentId(Long id);
}
