package com.example.hris.data_overtime;


import java.util.List;

import com.example.hris.data_overtime.data_overtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_overtime.data_overtime_repository;

@Service
public class data_overtime_service {
	
	@Autowired
    private data_overtime_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_overtime> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_overtime");
        return repo.findAll(pageRequest);
    }
	public List<data_overtime> listAll() {
        return repo.findAll();
    }
     
    public void save(data_overtime std) {
        repo.save(std);
    }
     
    public data_overtime get(String id_overtime) {
        return repo.findById(id_overtime).get();
    }
     
    public void delete(String id_overtime) {
        repo.deleteById(id_overtime);
    }

}