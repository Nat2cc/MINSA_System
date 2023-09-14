package com.upc.minsa.repositories;

import com.upc.minsa.entities.HealthCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface HealthCenterRepository extends JpaRepository<HealthCenter,Long> {
    public List<HealthCenter> findAllByOrderByNameAsc();
}
