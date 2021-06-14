package com.example.hris.data_document_request;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hris.data_document_request.data_document_request;


@Repository
public interface data_document_request_repository extends JpaRepository<data_document_request, String> {

}