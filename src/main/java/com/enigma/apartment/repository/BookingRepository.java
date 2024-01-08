package com.enigma.apartment.repository;

import com.enigma.apartment.entity.Booking;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface BookingRepository extends JpaRepository<Booking, String>, JpaSpecificationExecutor<Booking> {

    @Modifying
    @Query(value = "INSERT INTO t_booking(id, in_date, out_date, apartment_id, tenant_id) VALUES (gen_random_uuid(), :inDate, :outDate, :apartment, :tenant)", nativeQuery = true)
    void createBooking(@Param("inDate") Date inDate, @Param("outDate") Date outDate, @Param("apartment") String apartment, @Param("tenant") String tenant);


    @Query(value = "SELECT * FROM t_booking", nativeQuery = true)
    List<Booking> getAllBooking();

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_booking SET in_date = :inDate, out_date = :outDate, apartment_id = :apartment, tenant_id = :tenant",nativeQuery = true)
    void updateBooking(@Param("inDate") Date inDate, @Param("outDate") Date outDate, @Param("apartment") String apartment, @Param("tenant") String tenant);

    @Modifying
    @Query(value = "DELETE FROM t_booking WHERE id = :id", nativeQuery = true)
    void deleteBooking(@Param("id") String id);

    @Query(value = "SELECT * FROM t_booking WHERE id = ?1", nativeQuery = true)
    Optional<Booking> findByIdBooking(String id);
}
