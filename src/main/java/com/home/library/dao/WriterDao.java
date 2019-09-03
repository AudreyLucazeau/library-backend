package com.home.library.dao;

import com.home.library.entity.Writer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterDao extends CrudRepository<Writer, Long> {

}
