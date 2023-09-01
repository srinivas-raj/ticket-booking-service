package com.veda.online.repository;

import com.veda.online.entity.BookingTypeDetails;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface BookingTypeDetailsRepo {
    @Select("select * from BookingTypeDetails WHERE parentId=#{parentId}")
    List<BookingTypeDetails> findByParentId(Integer parentId);
    BookingTypeDetails findByTypeId(String id);
    @Insert("insert into BookingTypeDetails(typeId,totalSeats,availableSeats,parentId) " +
            "values(#{typeId},#{totalSeats},#{availableSeats},#{parentId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(BookingTypeDetails bookingTypeDetails);
}
