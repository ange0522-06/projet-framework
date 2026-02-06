package com.framework.backend.repository;

import com.framework.backend.model.ReservationTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationTempRepository extends JpaRepository<ReservationTemp, Long> {

    @Query("SELECT r FROM ReservationTemp r WHERE " +
           "(:startDateTime IS NULL OR r.dateheure >= :startDateTime) AND " +
           "(:endDateTime IS NULL OR r.dateheure <= :endDateTime)")
    List<ReservationTemp> findByDateRange(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );
}
