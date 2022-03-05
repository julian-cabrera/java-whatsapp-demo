package com.jcabrera.whatsappdemo.repository;

import com.jcabrera.whatsappdemo.model.ApiClient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiClientRepository extends JpaRepository<ApiClient, Long>{
  @Query("select a from ApiClient a where a.instanceId like %?1")
  public ApiClient findByInstanceNumber(int instanceNumber);
}
