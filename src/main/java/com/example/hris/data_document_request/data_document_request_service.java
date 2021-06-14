package com.example.hris.data_document_request;


import java.util.List;

import com.example.hris.data_document_request.data_document_request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_document_request.data_document_request_repository;

@Service
public class data_document_request_service {
	
	@Autowired
    private data_document_request_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_document_request> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_document_request");
        return repo.findAll(pageRequest);
    }
	public List<data_document_request> listAll() {
        return repo.findAll();
    }
     
    public void save(data_document_request std) {
        repo.save(std);
    }
     
    public data_document_request get(String id_document_request) {
        return repo.findById(id_document_request).get();
    }
     
    public void delete(String id_document_request) {
        repo.deleteById(id_document_request);
    }

}