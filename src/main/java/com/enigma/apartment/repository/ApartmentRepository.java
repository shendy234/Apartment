package com.enigma.apartment.repository;

import com.enigma.apartment.entity.Apartment;
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
public interface ApartmentRepository extends JpaRepository<Apartment,String>, JpaSpecificationExecutor<Apartment> {

    @Modifying
    @Query(value = "INSERT INTO m_apartment(id, address_apart, unit_apart, price, description, is_active, owner_Id) VALUES (gen_random_uuid(), :addressApart, :unitApart, :price, :description, :isActive, :ownerId)", nativeQuery = true)
    void createApartment(@Param("addressApart") String addressApart, @Param("unitApart") Integer unitApart, @Param("price") Long price, @Param("description") String description, @Param("isActive") Boolean isActive, @Param("ownerId") String ownerId);


    @Query(value = "SELECT * FROM m_apartment", nativeQuery = true)
    List<Apartment> getAllApartment();

    @Modifying
    @Transactional
    @Query(value = "UPDATE m_apartment SET address_apart = :addressApart, unit_apart = :unitApart, price = :price, description = :description, is_active = :isActive, owner_id = :ownerId WHERE id = :id",nativeQuery = true)
    void updateApartment(@Param("id") String id, @Param("addressApart") String addressApart, @Param("unitApart")Integer unitApart, @Param("price") Long price, @Param("description")String description, @Param("isActive") Boolean isActive, @Param("ownerId") String ownerId);

    @Modifying
    @Query(value = "DELETE FROM m_apartment WHERE id = :id", nativeQuery = true)
    void deleteApartment(@Param("id") String id);

    @Query(value = "SELECT * FROM m_apartment WHERE id = ?1", nativeQuery = true)
    Optional<Apartment> findByIdApartment(String id);

    @Query(value = "SELECT * FROM m_apartment a WHERE a.owner_id = :owner AND a.is_active = :isActive",nativeQuery = true)
    Optional<Apartment> findByOwnerIdAndIsActive(@Param("owner") Owner owner, @Param("isActive") Boolean isActive);
}
