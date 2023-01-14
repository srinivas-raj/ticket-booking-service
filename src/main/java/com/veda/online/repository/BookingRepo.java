package com.veda.online.repository;

import com.veda.online.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<BookingEntity,Long> {
    BookingEntity findByBookingType(String name);
}
