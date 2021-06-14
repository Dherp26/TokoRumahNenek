package com.example.hris.data_admin;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_admin.data_admin;


@Repository
public interface data_admin_repository extends JpaRepository<data_admin, String> {

}