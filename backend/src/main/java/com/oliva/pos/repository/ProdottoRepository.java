package com.oliva.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oliva.pos.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, String>{
    
}
