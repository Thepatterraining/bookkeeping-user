package com.zt.bookkeeping.user.domain.model;

import java.time.LocalDateTime;

/**
 * Desc:
 * ------------------------------------
 * Author:zt@meituan.com
 * Date:2025/7/10
 * Time:20:27
 */
public class User {
 private Long id;
 private String username;
 private String password;
 private String email;
 private String phone;
 private LocalDateTime createTime;
 private LocalDateTime updateTime;

 // 构造函数
 public User() {}

 public User(String username, String password, String email, String phone) {
  this.username = username;
  this.password = password;
  this.email = email;
  this.phone = phone;
  this.createTime = LocalDateTime.now();
  this.updateTime = LocalDateTime.now();
 }

 // 领域行为方法
 public void changePassword(String newPassword) {
  if (newPassword == null || newPassword.trim().isEmpty()) {
   throw new IllegalArgumentException("密码不能为空");
  }
  this.password = newPassword;
  this.updateTime = LocalDateTime.now();
 }

 public void updateProfile(String email, String phone) {
  this.email = email;
  this.phone = phone;
  this.updateTime = LocalDateTime.now();
 }


 // Getters
 public Long getId() { return id; }
 public String getUsername() { return username; }
 public String getPassword() { return password; }
 public String getEmail() { return email; }
 public String getPhone() { return phone; }
 public LocalDateTime getCreateTime() { return createTime; }
 public LocalDateTime getUpdateTime() { return updateTime; }

 // Setters (仅供基础设施层使用)
 public void setId(Long id) { this.id = id; }
 public void setUsername(String username) { this.username = username; }
 public void setPassword(String password) { this.password = password; }
 public void setEmail(String email) { this.email = email; }
 public void setPhone(String phone) { this.phone = phone; }
 public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
 public void setUpdateTime(LocalDateTime updateTime) { this.updateTime = updateTime; }
}
