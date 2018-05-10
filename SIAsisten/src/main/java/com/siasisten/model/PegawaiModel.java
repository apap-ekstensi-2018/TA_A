package com.siasisten.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PegawaiModel {
	@JsonProperty("id")
	private int id;
	@JsonProperty("nip")
	private String nip;
	@JsonProperty("nama")
	private String nama;
	//@JsonProperty("isStaf")
	//private boolean isStaf;
}
