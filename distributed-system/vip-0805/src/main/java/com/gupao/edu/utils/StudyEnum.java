package com.gupao.edu.utils;



public enum StudyEnum {
	
	
	
	E00000("00000","成功"){
		@Override
		public String getInfo() {
			return E00000.getDes();
		}
	},
	E00001("00001","失败"){
		@Override
		public String getInfo() {
			return E00001.getDes();
		}
	},
	E00002("00002","缺少参数"){
		@Override
		public String getInfo() {
			return E00002.getDes();
		}
	};
	
	private  String code;
	private  String  des;
	
	private StudyEnum(String code,String des) {
		this.code=code;
		this.des=des;
	}
	
	public  String getCode() {
		return  this.code;
	}
	public  String getDes() {
		return this.des;
	}
	@Override
	public String  toString(){
		return "C["+this.code+"],M["+this.des+"]";
	}
	
	//抽象方法
	public abstract String getInfo();
	public static void main(String[] args) {
		for(StudyEnum se:StudyEnum.values()) {
			System.out.println(se.getCode()+"==="+se.getDes());
			System.out.println("***************");
			System.out.println("调用对应的抽象方法=>"+se.getInfo());
			System.out.println(se.toString());
		}
	}
}