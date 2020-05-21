package com.my.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crud.bean.Student;
import com.my.crud.bean.StudentExample;
import com.my.crud.bean.StudentExample.Criteria;
import com.my.crud.dao.StudentMapper;

/**
 * ��ѯ����ѧ��
 *
 */
@Service
public class StudentService {
	
	@Autowired
	StudentMapper studentMapper;
	
	/**
	 * ��ѯ����ѧ��
	 * @return
	 */
	public List<Student> getAll() {
		StudentExample example = new StudentExample();
		//������� ����id ����
		example.setOrderByClause("id asc");
		return studentMapper.selectByExampleWithpos(example);
	}

	/**
	 * ���ѧ��
	 * @param student
	 */
	public void saveStu(Student student) {
		studentMapper.insertSelective(student);
	}

	/**
	 * У��ѧ�����Ƿ����
	 * 1.֧��JSR303У��
	 * 2.����hibernate-Validator
	 * @param name
	 * @return ture:����    false:������
	 */
	public boolean checkuser(String name) {
		StudentExample example = new StudentExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		long count = studentMapper.countByExample(example);
		return count == 0;
	}

	/**
	 * �޸�ѧ����Ϣ
	 * @param id
	 * @return 
	 */
	public Student getStu(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	/**
	 * ����ѧ����Ϣ
	 * @param student
	 */
	public void updateStu(Student student) {
		studentMapper.updateByPrimaryKeySelective(student);
	}

	/**
	 * ɾ������ѧ��
	 * @param id
	 */
	public void deleteStu(Integer id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	/**
	 * ����ɾ��
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
