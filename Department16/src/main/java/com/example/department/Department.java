package com.example.department;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {

@Id
@Column(name="departmet_id")
private int id;
@Column(name="departmet_name")
private String name;
@Column(name="location_id")
private int location;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getLocation() {
	return location;
}
public void setLocation(int location) {
	this.location = location;
}
@Override
public String toString() {
	return "Department [id=" + id + ", name=" + name + ", location=" + location + "]";
}
}