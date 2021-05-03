package com.infrrd.cachemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infrrd.cachemanager.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
