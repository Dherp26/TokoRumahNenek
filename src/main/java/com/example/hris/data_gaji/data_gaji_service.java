package com.example.hris.data_gaji;


import java.util.List;

import com.example.hris.data_gaji.data_gaji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_gaji.data_gaji_repository;

@Service
public class data_gaji_service {
	
	@Autowired
    private data_gaji_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_gaji> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_gaji");
        return repo.findAll(pageRequest);
    }
	public List<data_gaji> listAll() {
        return repo.findAll();
    }
     
    public void save(data_gaji std) {
        repo.save(std);
    }
     
    public data_gaji get(String id_gaji) {
        return repo.findById(id_gaji).get();
    }
     
    public void delete(String id_gaji) {
        repo.deleteById(id_gaji);
    }

}