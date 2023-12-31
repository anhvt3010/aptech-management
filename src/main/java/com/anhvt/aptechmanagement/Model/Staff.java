package com.anhvt.aptechmanagement.Model;

import java.time.LocalDate;

public class Staff {
    private int id;
    private Role role;
    private String first_name;
    private String last_name;
    private String code;
    private String email;
    private String phone;
    private String username;
    private String password;
    private LocalDate birth;
    private String profile;
    private Byte status;


 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public Role getRole() {
  return role;
 }

 public void setRole(Role role) {
  this.role = role;
 }

 public String getFirst_name() {
  return first_name;
 }

 public void setFirst_name(String first_name) {
  this.first_name = first_name;
 }

 public String getLast_name() {
  return last_name;
 }

 public void setLast_name(String last_name) {
  this.last_name = last_name;
 }

 public String getCode() {
  return code;
 }

 public void setCode(String code) {
  this.code = code;
 }

 public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getPhone() {
  return phone;
 }

 public void setPhone(String phone) {
  this.phone = phone;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public LocalDate getBirth() {
  return birth;
 }

 public void setBirth(LocalDate birth) {
  this.birth = birth;
 }

 public String getProfile() {
  return profile;
 }

 public void setProfile(String profile) {
  this.profile = profile;
 }

 public Byte getStatus() {
  return status;
 }

 public void setStatus(Byte status) {
  this.status = status;
 }
}
