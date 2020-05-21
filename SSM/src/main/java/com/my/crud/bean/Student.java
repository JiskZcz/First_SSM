package com.my.crud.bean;

import javax.validation.constraints.Pattern;

public class Student {
    private Integer id;
    
    @Pattern(regexp = "((^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5}))"
    		,message = "用户名必须是2-5位中文或者6-16位英文和数字的组合")
    private String name;

    private String sex;

    private Integer posit;
    
    private Pos pos;
    
    
	public Student(Integer id, String name, String sex, Integer posit) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.posit = posit;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pos getPos() {
		return pos;
	}

	public void setPos(Pos pos) {
		this.pos = pos;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getPosit() {
        return posit;
    }

    public void setPosit(Integer posit) {
        this.posit = posit;
    }
}