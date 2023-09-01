package com.veda.online.repository;

import com.veda.online.entity.BookingEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


@Mapper
public interface BookingRepo {
    @Select("select * from BookingEntity WHERE bookingType=#{name}")
    BookingEntity findByBookingType(String name);

    @Insert("insert into BookingEntity(bookingType) values(#{bookingType})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(BookingEntity bookingEntity);
}
