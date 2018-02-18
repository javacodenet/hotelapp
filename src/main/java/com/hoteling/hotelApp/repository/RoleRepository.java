package com.hoteling.hotelApp.repository;

import com.hoteling.hotelApp.domain.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
