package com.cg.irs.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;


@SuppressWarnings("serial")
@Entity
@Table(name="users")
@NamedQueries(
		value={@NamedQuery(name="login",query="Select user from UserBean user where user.userId=:userId and user.password=:password"),
				@NamedQuery(name="getAllUsers",query="from UserBean userBean where userBean.userId not in(:userId)")})
public class UserBean implements Serializable
{
	

	
	public UserBean() {
		super();
	}


	public UserBean(String userId) {
		super();
		this.userId = userId;
	}
	
	
	@Id
	@Column(name="users_id")
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	//@Pattern(regexp="ADMIN|RM|RMGE",message="Role Shiuld Be ADMIN|RM|RMGE")
	private String role;

	
	// One to Many in  ProjectBean  
	@OneToMany(mappedBy="userBean",cascade=CascadeType.ALL)
	Set<ProjectBean> projectBean = new HashSet<>(); 
	
	// One to Many Requisition Bean
	@OneToMany(mappedBy="userBean",cascade=CascadeType.ALL)
	Set<RequisitionBean> requisitionBean = new HashSet<>(); 
	
	
	// One to Many Assigned Requisition
	@OneToMany(mappedBy="userBean",cascade=CascadeType.ALL)
	Set<AssignedRequisitionBean> assignedRequisitionBean = new HashSet<>();


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Set<ProjectBean> getProjectBean() {
		return projectBean;
	}


	public void setProjectBean(Set<ProjectBean> projectBean) {
		this.projectBean = projectBean;
	}


	public Set<RequisitionBean> getRequisitionBean() {
		return requisitionBean;
	}


	public void setRequisitionBean(Set<RequisitionBean> requisitionBean) {
		this.requisitionBean = requisitionBean;
	}


	public Set<AssignedRequisitionBean> getAssignedRequisitionBean() {
		return assignedRequisitionBean;
	}


	public void setAssignedRequisitionBean(
			Set<AssignedRequisitionBean> assignedRequisitionBean) {
		this.assignedRequisitionBean = assignedRequisitionBean;
	}


	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", password=" + password
				+ ", role=" + role + ", projectBean=" + projectBean
				+ ", requisitionBean=" + requisitionBean
				+ ", assignedRequisitionBean=" + assignedRequisitionBean + "]";
	}
	
	
}
