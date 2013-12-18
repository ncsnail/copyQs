package org.wind.qs.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.wind.qs.entity.Task;

public interface TaskDao extends PagingAndSortingRepository<Task, Long>,JpaSpecificationExecutor<Task> {

}
