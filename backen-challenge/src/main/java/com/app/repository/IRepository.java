package com.app.repository;


import java.util.List;
import java.util.Map;


public interface IRepository<T> {
  List<T> findByCriteria(Map<String, Object> criteria);

}


