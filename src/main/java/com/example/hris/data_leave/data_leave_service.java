package com.example.hris.data_leave;


import java.util.List;

import com.example.hris.data_leave.data_leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_leave.data_leave_repository;

@Service
public class data_leave_service {
	
	@Autowired
    private data_leave_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_leave> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_leave");
        return repo.findAll(pageRequest);
    }
	public List<data_leave> listAll() {
        return repo.findAll();
    }
     
    public void save(data_leave std) {
        repo.save(std);
    }
     
    public data_leave get(String id_leave) {
        return repo.findById(id_leave).get();
    }
     
    public void delete(String id_leave) {
        repo.deleteById(id_leave);
    }

}