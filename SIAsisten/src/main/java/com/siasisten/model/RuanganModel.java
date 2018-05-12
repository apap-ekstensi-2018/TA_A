package com.siasisten.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuanganModel {
	@JsonProperty("id")
	private int id;
	@JsonProperty("nama")
	private String nama;
	@JsonProperty("kapasitas")
	private int kapasitas;
}
