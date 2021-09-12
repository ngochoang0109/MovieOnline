package com.tlcn.movieonline.repository;

import com.tlcn.movieonline.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
