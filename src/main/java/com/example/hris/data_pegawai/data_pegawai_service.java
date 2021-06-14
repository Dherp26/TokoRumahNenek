package com.example.hris.data_pegawai;


import java.util.List;

import com.example.hris.data_pegawai.data_pegawai;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.hris.data_pegawai.data_pegawai_repository;

@Service
public class data_pegawai_service {
	
	@Autowired
    private data_pegawai_repository repo;

    private static final int PAGE_SIZE = 5;

    public Page<data_pegawai> listAll_pagenumber(Integer pageNumber) {
        PageRequest pageRequest =
                PageRequest.of(pageNumber - 1, PAGE_SIZE, Sort.Direction.ASC, "id_pegawai");
        return repo.findAll(pageRequest);
    }
	public List<data_pegawai> listAll() {
        return repo.findAll();
    }
     
    public void save(data_pegawai std) {
        repo.save(std);
    }
     
    public data_pegawai get(String id_pegawai) {
        return repo.findById(id_pegawai).get();
    }
     
    public void delete(String id_pegawai) {
        repo.deleteById(id_pegawai);
    }

}