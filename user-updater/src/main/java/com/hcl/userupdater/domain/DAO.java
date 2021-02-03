package com.hcl.userupdater.domain;

import java.util.List;

public interface DAO<T> {
  List<T> getAll();
  T get(Long id);
  T add(T entity);
  void delete(Long id);
  void update(T entity);
}
