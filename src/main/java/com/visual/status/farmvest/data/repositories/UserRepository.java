package com.visual.status.farmvest.data.repositories;

import com.visual.status.farmvest.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User, Long> {

    boolean existsByEmail(String email);

}
