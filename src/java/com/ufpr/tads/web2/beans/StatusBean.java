/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufpr.tads.web2.beans;

/**
 *
 * @author nilo-
 */
public class StatusBean {
	private int id;
	private String desc;

	public StatusBean() {
	}

	public StatusBean(int id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
