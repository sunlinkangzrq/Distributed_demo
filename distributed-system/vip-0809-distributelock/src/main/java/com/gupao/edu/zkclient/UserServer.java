package com.gupao.edu.zkclient;

import java.io.Serializable;

public class UserServer  implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mc_id;
	private String mc_name;
	public int getMc_id() {
		return mc_id;
	}
	public void setMc_id(int mc_id) {
		this.mc_id = mc_id;
	}
	public String getMc_name() {
		return mc_name;
	}
	public void setMc_name(String mc_name) {
		this.mc_name = mc_name;
	}
	@Override
	public String toString() {
		return "UserServer [mc_id=" + mc_id + ", mc_name=" + mc_name + "]";
	}
	
}
