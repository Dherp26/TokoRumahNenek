package com.example.hris.data_absensi;


import java.util.List;

import com.example.hris.data_absensi.data_absensi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_absensi.data_absensi_repository;

@Service
public class data_absensi_service {
	
	@Autowired
    private data_absensi_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_absensi> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_absensi");
        return repo.findAll(pageRequest);
    }
	public List<data_absensi> listAll() {
        return repo.findAll();
    }
     
    public void save(data_absensi std) {
        repo.save(std);
    }
     
    public data_absensi get(String id_absensi) {
        return repo.findById(id_absensi).get();
    }
     
    public void delete(String id_absensi) {
        repo.deleteById(id_absensi);
    }

}