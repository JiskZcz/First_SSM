package com.my.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.crud.bean.Pos;
import com.my.crud.dao.PosMapper;

@Service
public class PosService {
	
	@Autowired
	private PosMapper posMapper;
	
	public List<Pos> getPosAll(){
		List<Pos> list = posMapper.selectByExample(null);
		return list;
	}
}
