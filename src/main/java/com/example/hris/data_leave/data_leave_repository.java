package com.example.hris.data_leave;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_leave.data_leave;


@Repository
public interface data_leave_repository extends JpaRepository<data_leave, String> {

}