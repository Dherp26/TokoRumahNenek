package com.example.hris.data_gaji;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_gaji.data_gaji;


@Repository
public interface data_gaji_repository extends JpaRepository<data_gaji, String> {

}