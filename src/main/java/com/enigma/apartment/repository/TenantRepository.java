package com.enigma.apartment.repository;

import com.enigma.apartment.entity.Tenant;
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
public interface TenantRepository extends JpaRepository<Tenant, String>, JpaSpecificationExecutor<Tenant> {
    @Modifying
    @Query(value = "INSERT INTO m_tenant(id,full_name,phone_number) VALUES (gen_random_uuid(),:fullName,:phoneNumber)",nativeQuery = true)
    void createTenant (@Param("fullName") String fullName, @Param("phoneNumber") String phoneNumber);
    @Query(value = "SELECT * FROM m_tenant", nativeQuery = true)
    List<Tenant> getAllTenants();
    @Modifying
    @Transactional
    @Query(value = "UPDATE m_tenant SET full_name = :fullName, phone_number = :phoneNumber WHERE id = :id", nativeQuery = true)
    void updateTenant(@Param("id") String id, @Param("fullName") String fullName, @Param("phoneNumber") String phoneNumber);
    @Modifying
    @Query(value = "DELETE FROM m_tenant WHERE id = :id", nativeQuery = true)
    void deleteTenant(@Param("id") String id);
    @Query(value = "SELECT * FROM m_tenant WHERE id = ?1", nativeQuery = true)
    Optional<Tenant> findByIdTenant(String id);
}
