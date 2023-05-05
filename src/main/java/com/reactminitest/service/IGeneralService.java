package com.reactminitest.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService<E> {
    List<E> getAll();
    Optional<E> getById(Long id);
    E save(E e);
    void delete(Long id);
}
