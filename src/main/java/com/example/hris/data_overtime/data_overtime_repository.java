package com.example.hris.data_overtime;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_overtime.data_overtime;


@Repository
public interface data_overtime_repository extends JpaRepository<data_overtime, String> {

}