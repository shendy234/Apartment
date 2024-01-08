package com.enigma.apartment.repository;

import com.enigma.apartment.entity.Owner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, String>, JpaSpecificationExecutor<Owner> {
    @Modifying
    @Query(value = "INSERT INTO m_owner(id,full_name,phone_number) VALUES (gen_random_uuid(),:fullName,:phoneNumber)",nativeQuery = true)
    void createOwner (@Param("fullName") String fullName, @Param("phoneNumber") String phoneNumber);

    @Query(value = "SELECT * FROM m_owner", nativeQuery = true)
    List<Owner> getAllOwners();

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_owner SET full_name = :fullName, phone_number = :phoneNumber WHERE id = :id", nativeQuery = true)
    void updateOwner(@Param("id") String id, @Param("fullName") String fullName, @Param("phoneNumber") String phoneNumber);
    @Modifying
    @Query(value = "DELETE FROM m_owner WHERE id = :id", nativeQuery = true)
    void deleteOwner(@Param("id") String id);

    @Query(value = "SELECT * FROM m_owner WHERE id = ?1", nativeQuery = true)
    Optional<Owner> findByIdOwner( String id);

}
