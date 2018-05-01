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
}
