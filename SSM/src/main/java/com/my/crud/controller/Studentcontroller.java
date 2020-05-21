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
	 * 删除单个或批量学生信息
	 * 批量删除 1-2-3 id由-隔开
	 */
	@ResponseBody
	@RequestMapping(value = "stu/{ids}",method = RequestMethod.DELETE)
	public Msg deleteStuByid(@PathVariable("ids") String ids) {
		//批量删除
		if(ids.contains("-")) {
			List<Integer> list = new ArrayList<>();
			String[] id = ids.split("-");
			for (String string : id) {
				list.add(Integer.parseInt(string));
			}	
			studentService.deleteBatch(list);
		}
		else {
			//单个删除
			Integer id = Integer.parseInt(ids);
			studentService.deleteStu(id);
		}
		return Msg.success();
	}
	
	/**
	 * 更新方法
	 */
	@ResponseBody
	@RequestMapping(value = "stu/{id}",method = RequestMethod.PUT)
	public Msg updateStu(Student student) {
		studentService.updateStu(student);
		return Msg.success();
	}
	
	
	@ResponseBody
	@RequestMapping(value = "stu/{id}",method = RequestMethod.GET)
	//@PathVariable() 从路径中获取
	public Msg getStu(@PathVariable("id") Integer id) {
		Student student = studentService.getStu(id);
		return Msg.success().add("stu", student);
	}
	
	
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkStuName(@RequestParam("stuName")String name) {
		//先判断用户名是否是合法的表达式
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!name.matches(regex)) {
			return Msg.fail().add("va_msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
		}
		//数据库校验
		boolean b = studentService.checkuser(name);
		if(b) {
			return Msg.success();
		}
		else	
		return Msg.fail().add("va_msg", "用户名不可用");
	}
	
	/**
	 * 学生保存
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stus",method = RequestMethod.POST)
	public Msg saveStu(@Valid Student student,BindingResult result) {
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<String, Object>();
			//校验失败，返回失败 模态框中显示信息
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("错误的字段名："+fieldError.getField());
				System.out.println("错误信息："+fieldError.getDefaultMessage());
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
	 * 导入jackson包
	 * 返回json数据
	 * @param pn
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stus",method = RequestMethod.GET)
	public Msg getSstusWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		List<Student> stus = studentService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
		PageInfo page = new PageInfo(stus, 5);
		return Msg.success().add("pageInfo", page);
	}

	/**
	 * 查询学生数据（分页查询）
	 * 
	 * @return
	 */
	//@RequestMapping("/stus")
	//public String getStus(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		// 这不是一个分页查询；
		// 引入PageHelper分页插件
		// 在查询之前只需要调用，传入页码，以及每页的大小
	//	PageHelper.startPage(pn, 5);
		// startPage后面紧跟的这个查询就是一个分页查询
		//List<Student> stus = studentService.getAll();
		// 使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了。
		// 封装了详细的分页信息,包括有我们查询出来的数据，传入连续显示的页数
	//	PageInfo page = new PageInfo(stus, 5);
		//model.addAttribute("pageInfo", page);

		//return "list";
	//}
}
