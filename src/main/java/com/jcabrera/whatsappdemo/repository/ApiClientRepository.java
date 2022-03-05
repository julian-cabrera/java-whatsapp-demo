package com.jcabrera.whatsappdemo.repository;

import com.jcabrera.whatsappdemo.model.ApiClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClient, Long>{
  
}
