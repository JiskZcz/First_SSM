package com.my.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.crud.bean.Msg;
import com.my.crud.bean.Student;
import com.my.crud.service.StudentService;

@Controller
public class Studentcontroller {

	@Autowired
	StudentService studentService;

	/**
	 * ɾ������������ѧ����Ϣ
	 * ����ɾ�� 1-2-3 id��-����
	 */
	@ResponseBody
	@RequestMapping(value = "stu/{ids}",method = RequestMethod.DELETE)
	public Msg deleteStuByid(@PathVariable("ids") String ids) {
		//����ɾ��
		if(ids.contains("-")) {
			List<Integer> list = new ArrayList<>();
			String[] id = ids.split("-");
			for (String string : id) {
				list.add(Integer.parseInt(string));
			}	
			studentService.deleteBatch(list);
		}
		else {
			//����ɾ��
			Integer id = Integer.parseInt(ids);
			studentService.deleteStu(id);
		}
		return Msg.success();
	}
	
	/**
	 * ���·���
	 */
	@ResponseBody
	@RequestMapping(value = "stu/{id}",method = RequestMethod.PUT)
	public Msg updateStu(Student student) {
		studentService.updateStu(student);
		return Msg.success();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "stu/{id}",method = RequestMethod.GET)
	//@PathVariable() ��·���л�ȡ
	public Msg getStu(@PathVariable("id") Integer id) {
		Student student = studentService.getStu(id);
		return Msg.success().add("stu", student);
	}
	
	
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkStuName(@RequestParam("stuName")String name) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!name.matches(regex)) {
			return Msg.fail().add("va_msg", "�û���������2-5λ���Ļ���6-16λӢ�ĺ����ֵ����");
		}
		//���ݿ�У��
		boolean b = studentService.checkuser(name);
		if(b) {
			return Msg.success();
		}
		else	
		return Msg.fail().add("va_msg", "�û���������");
	}
	
	/**
	 * ѧ������
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stus",method = RequestMethod.POST)
	public Msg saveStu(@Valid Student student,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<String, Object>();
			//У��ʧ�ܣ�����ʧ�� ģ̬������ʾ��Ϣ
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}
		else {
		studentService.saveStu(student);
		return Msg.success();
		}
	}
	
	
	/**
	 * ����jackson��
	 * ����json����
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stus",method = RequestMethod.GET)
	public Msg getSstusWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// �ⲻ��һ����ҳ��ѯ��
		// ����PageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ���ã�����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		// startPage��������������ѯ����һ����ҳ��ѯ
		List<Student> stus = studentService.getAll();
		// ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ������ˡ�
		// ��װ����ϸ�ķ�ҳ��Ϣ,���������ǲ�ѯ���������ݣ�����������ʾ��ҳ��
		PageInfo page = new PageInfo(stus, 5);
		return Msg.success().add("pageInfo", page);
	}

	/**
	 * ��ѯѧ�����ݣ���ҳ��ѯ��
	 * 
	 * @return
	 */
	//@RequestMapping("/stus")
	//public String getStus(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// �ⲻ��һ����ҳ��ѯ��
		// ����PageHelper��ҳ���
		// �ڲ�ѯ֮ǰֻ��Ҫ���ã�����ҳ�룬�Լ�ÿҳ�Ĵ�С
	//	PageHelper.startPage(pn, 5);
		// startPage��������������ѯ����һ����ҳ��ѯ
		//List<Student> stus = studentService.getAll();
		// ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ������ˡ�
		// ��װ����ϸ�ķ�ҳ��Ϣ,���������ǲ�ѯ���������ݣ�����������ʾ��ҳ��
	//	PageInfo page = new PageInfo(stus, 5);
		//model.addAttribute("pageInfo", page);

		//return "list";
	//}
}
