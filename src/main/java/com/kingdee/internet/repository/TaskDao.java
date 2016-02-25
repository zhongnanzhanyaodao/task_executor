package com.kingdee.internet.repository;

import com.kingdee.internet.entity.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskDao extends CrudRepository<Task, String> {

}
