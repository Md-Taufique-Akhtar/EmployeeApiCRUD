package com.Scalive.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Scalive.Entity.Emp;
@Repository
public interface EmpRepository extends JpaRepository<Emp, Integer> {
	public Optional<Emp> findByEname(String ename);
}
