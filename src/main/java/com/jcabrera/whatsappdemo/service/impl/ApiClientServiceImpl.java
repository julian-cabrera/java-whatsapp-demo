package com.jcabrera.whatsappdemo.service.impl;

import com.jcabrera.whatsappdemo.exception.ResourceNotFoundException;
import com.jcabrera.whatsappdemo.model.ApiClient;
import com.jcabrera.whatsappdemo.repository.ApiClientRepository;
import com.jcabrera.whatsappdemo.service.ApiClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ApiClientServiceImpl implements ApiClientService {
  
  @Autowired
  ApiClientRepository repo;

  @Override
  public ApiClient get(Long id) {
    return repo.getById(id);
  }

  @Override
  public ApiClient findByInstanceNumber(int instanceNumber) {
    return repo.findByInstanceNumber(instanceNumber);
  }

  @Override
  public ApiClient save(ApiClient client) {
    return repo.save(client);
  }

  @Override
  public ApiClient update(Long id, ApiClient client) {
    ApiClient actualClient = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Client with ID %s could not be found. Could not update Client", id)));
    actualClient.update(client);
    return actualClient;
  }

  @Override
  public ApiClient delete(Long id) {
    ApiClient client = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Client with ID %s could not be found. Could not delete Client", id)));
    return client;
  }
  
}
