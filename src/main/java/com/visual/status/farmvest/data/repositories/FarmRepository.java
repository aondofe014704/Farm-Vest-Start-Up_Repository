package com.visual.status.farmvest.data.repositories;

import com.visual.status.farmvest.data.models.Farm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository< Farm, Long> {
}
