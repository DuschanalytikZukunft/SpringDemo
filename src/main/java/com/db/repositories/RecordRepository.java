package com.db.repositories;


import org.springframework.data.repository.CrudRepository;

import com.db.entities.Record;

public interface RecordRepository extends CrudRepository<Record, Integer> {

}
