package com.siasisten.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MahasiswaModel {
	@JsonProperty("id")
	private int id;
	
	@JsonProperty("nama")
	private String nama;
	
	@JsonProperty("npm")
	private String npm;
	
	@JsonProperty("mataKuliahList")
	private List<MatkulModel> mataKuliahList;
}
