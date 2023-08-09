package ru.t1.test.demorest.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.t1.test.demorest.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  boolean existsByEmail(String email);

  boolean existsByPhone(String phone);

  Optional<UserEntity> getByIdAndPhone(UUID userId,String phone);
}
