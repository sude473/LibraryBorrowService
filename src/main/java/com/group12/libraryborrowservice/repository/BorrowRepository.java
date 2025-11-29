package com.group12.libraryborrowservice.repository;

import com.group12.libraryborrowservice.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

}