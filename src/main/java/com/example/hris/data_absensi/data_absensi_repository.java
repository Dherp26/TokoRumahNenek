package com.example.hris.data_absensi;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_absensi.data_absensi;


@Repository
public interface data_absensi_repository extends JpaRepository<data_absensi, String> {

}