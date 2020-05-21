package com.my.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crud.bean.Student;
import com.my.crud.bean.StudentExample;
import com.my.crud.bean.StudentExample.Criteria;
import com.my.crud.dao.StudentMapper;

/**
 * 查询所有学生
 *
 */
@Service
public class StudentService {
	
	@Autowired
	StudentMapper studentMapper;
	
	/**
	 * 查询所有学生
	 * @return
	 */
	public List<Student> getAll() {
		StudentExample example = new StudentExample();
		//添加排序 根据id 升序
		example.setOrderByClause("id asc");
		return studentMapper.selectByExampleWithpos(example);
	}

	/**
	 * 添加学生
	 * @param student
	 */
	public void saveStu(Student student) {
		studentMapper.insertSelective(student);
	}

	/**
	 * 校验学生名是否可用
	 * 1.支持JSR303校验
	 * 2.导入hibernate-Validator
	 * @param name
	 * @return ture:可用    false:不可用
	 */
	public boolean checkuser(String name) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		long count = studentMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * 修改学生信息
	 * @param id
	 * @return 
	 */
	public Student getStu(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 更新学生信息
	 * @param student
	 */
	public void updateStu(Student student) {
		studentMapper.updateByPrimaryKeySelective(student);
	}

	/**
	 * 删除单个学生
	 * @param id
	 */
	public void deleteStu(Integer id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where id in(1,2,3)
		criteria.andIdIn(ids);
		studentMapper.deleteByExample(example);
	}
}
