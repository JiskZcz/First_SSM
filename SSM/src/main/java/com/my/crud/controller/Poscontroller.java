package com.my.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.crud.bean.Msg;
import com.my.crud.bean.Pos;
import com.my.crud.service.PosService;

@Controller
public class Poscontroller {
	
	@Autowired
	private PosService posService;
	
	@ResponseBody
	@RequestMapping("/positAll")
	public Msg getPosAll() {
		List<Pos> list = posService.getPosAll();
		return Msg.success().add("posits", list);
	}
}
