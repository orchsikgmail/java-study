package com.sp.member;

public class Member {

	// member
	private String id;
	private String pwd;
	private int enabled;
	private String name;
	private String created;
	private String modified;
	private String lasgLogin;
	// memberAuthority
	private String authority;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getLasgLogin() {
		return lasgLogin;
	}
	public void setLasgLogin(String lasgLogin) {
		this.lasgLogin = lasgLogin;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
