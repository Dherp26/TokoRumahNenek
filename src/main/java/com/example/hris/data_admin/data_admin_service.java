package com.example.hris.data_admin;


import java.util.List;

import com.example.hris.data_admin.data_admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_admin.data_admin_repository;

@Service
public class data_admin_service {
	
	@Autowired
    private data_admin_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_admin> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_admin");
        return repo.findAll(pageRequest);
    }
	public List<data_admin> listAll() {
        return repo.findAll();
    }
     
    public void save(data_admin std) {
        repo.save(std);
    }
     
    public data_admin get(String id_admin) {
        return repo.findById(id_admin).get();
    }
     
    public void delete(String id_admin) {
        repo.deleteById(id_admin);
    }

}