package com.hmsapp.repository;

import com.hmsapp.entity.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Reviews, Long> {

}