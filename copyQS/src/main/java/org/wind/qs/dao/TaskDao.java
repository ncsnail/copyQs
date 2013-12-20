package org.wind.qs.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.wind.qs.entity.Task;

public interface TaskDao extends PagingAndSortingRepository<Task, Long>,JpaSpecificationExecutor<Task> {

	@Modifying
	@Query("delete from Task t where t.user.id=?1")
	public void deleteByUserId(Long userId);
	
}
