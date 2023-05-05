package com.reactminitest.service;

import com.reactminitest.model.Role;
import com.reactminitest.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {
    @Autowired
    IRoleRepository repository;

    @Override
    public List<Role> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
