package org.wind.qs.service.task;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wind.qs.dao.TaskDao;
import org.wind.qs.entity.Task;

@Service("taskService")
@Transactional
public class TaskService {

	@Autowired
	TaskDao taskDao;
	
	public Page<Task> getUserTask(Long userId,String searcheParams,int pageNum,int pageSize,String sortType){
		PageRequest pageRequest = buildPageRequest(pageNum,pageSize,sortType);
		Specification<Task> spec = buildSpecification(userId,searcheParams);
		return taskDao.findAll(spec, pageRequest);
	}
	
	private PageRequest buildPageRequest(int pageNum,int pageSize,String sortType){
		
		Sort sort = null;
		if(sortType.equals("title")){
			sort = new Sort(Direction.ASC,"title");
		}else{
			sort = new Sort(Direction.DESC,"id");
		}
		return new PageRequest(pageNum - 1,pageSize,sort);
	}
	
	private Specification<Task> buildSpecification(final Long userId, final String searchParams){
		return new Specification<Task>(){
			@Override
			public Predicate toPredicate(Root<Task> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate pAll = null;
				pAll =  cb.equal(root.get("user").get("id").as(Long.class), userId);
				if(StringUtils.isNotBlank(searchParams)){
					Predicate  p2 =  cb.like(root.get("title").as(String.class), "%"+searchParams+"%");
					pAll = cb.and(pAll,p2);
				}
				return pAll;
			}
		};
	}
	
	public void saveTask(Task t) {
		taskDao.save(t);
	}
	
	public Task getTask(Long id){
		
		return taskDao.findOne(id);
	}
	
	public void deleteTask(Long id){
		taskDao.delete(id);
	}
	
}
