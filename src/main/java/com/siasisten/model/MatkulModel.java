package com.siasisten.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatkulModel {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("kode_matkul")
	private String kodeMatkul;
	
	@JsonProperty("nama_matkul")
	private String namaMatkul;
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;	 
	}
	
	public void setKodeMatkul(String kodeMatkul) {
		this.kodeMatkul = kodeMatkul;
	}
	
	public String getKodeMatkul() {
		return kodeMatkul;
	}
	
	public void setNamaMatkul(String namaMatkul) {
		this.namaMatkul = namaMatkul;
	}
	
	public String getNamaMatkul() {
		return namaMatkul;
	}
}
