package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.cache.Cacheable;
import com.demo.cache.Cacheable.KeyMode;
import com.demo.dao.StudentDao;
import com.demo.pojo.Student;
import com.demo.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;

	@Override
	@Cacheable(expire = 60)
	public List getAll() {
		return studentDao.getAll();
	}

	@Override
	@Cacheable(expire = 36, keyMode = KeyMode.ALL)
	public Student getById(int id) {
		return studentDao.getById(id);
	}

	@Override
	@Cacheable(expire = 36)
	public int getRows() {
		return studentDao.getRows();
	}

	@Override
	@Cacheable(expire = 36, keyMode = KeyMode.ALL)
	public List getByPage(Map map) {
		return studentDao.getByPage(map);
	}

	@Override
	@Cacheable(expire = 36, keyMode = KeyMode.ALL)
	public long getCount() {
		return studentDao.getCount();
	}

	@Override
	public List<Map<String, Object>> getStuByCondition(String name, String phone) {
		return studentDao.getStuByCondition(name, phone);
	}

	@Override
	public int insertStudent(Student student) {
		return studentDao.insertStudent(student);
	}

	@Override
	public List<Student> getStuPic() {
		return studentDao.getStuPic();
	}

	@Override
	public List<Student> getStuPicList(Student student) {
		return studentDao.getStuPicList(student);
	}

	@Override
	public int delMemUsers() {
		return studentDao.delMemUsers();
	}

	@Override
	public int insertStudentByMap(List<Map<String, Object>> list) {
		return studentDao.insertStudentByMap(list);
	}

	@Override
	public List<Map<String, Object>> getByTable(String table) {
		return studentDao.getByTable(table);
	}

}
