package com.mabeldlarek.web.repository;

import com.mabeldlarek.web.models.Role;
import com.mabeldlarek.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
