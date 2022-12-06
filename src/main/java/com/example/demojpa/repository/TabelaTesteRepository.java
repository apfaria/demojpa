package com.example.demojpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demojpa.beans.TabelaTeste;

public interface TabelaTesteRepository extends CrudRepository<TabelaTeste, Integer> {

    TabelaTeste findById(int id);

}
