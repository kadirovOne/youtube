package com.company.repository;

import com.company.entity.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {
    Optional<ProfileEntity> findByEmail(String email);

    Page<ProfileEntity> findAllByVisible(Boolean b, Pageable pageable);
    @Transactional
    @Modifying
    @Query("update ProfileEntity  p set p.password = ?2 where p.id = ?1")
    void updatePassword(Integer id, String newPassword);
    @Transactional
    @Modifying
    @Query("update ProfileEntity  p set p.username = ?2 where p.id = ?1")
    void updateUsername(Integer id, String username);

    @Transactional
    @Modifying
    @Query("update ProfileEntity  p set p.email = ?2 where p.id = ?1")
    void updateEmail(Integer id, String newEmail);
}
