package io.metadata.courses.repository;

import io.metadata.courses.model.Tradie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradieRepository extends JpaRepository<Tradie, Long> {
}
