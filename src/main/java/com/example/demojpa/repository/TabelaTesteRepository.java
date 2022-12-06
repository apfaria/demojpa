package com.example.demojpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demojpa.beans.TabelaTeste;

import jakarta.transaction.Transactional;

public interface TabelaTesteRepository extends CrudRepository<TabelaTeste, Integer> {

    // void updateTabelaTeste
    @Transactional
    @Modifying
    @Query("update TabelaTeste set nome = :nome where id = :id")
    void updateTabelaTeste(@Param("nome") String nome, @Param("id") int id);

    TabelaTeste findById(int id);

}
