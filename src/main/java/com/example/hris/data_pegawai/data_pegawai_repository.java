package com.example.hris.data_pegawai;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_pegawai.data_pegawai;


@Repository
public interface data_pegawai_repository extends JpaRepository<data_pegawai, String> {

}