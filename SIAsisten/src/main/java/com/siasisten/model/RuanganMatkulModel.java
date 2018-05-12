package com.siasisten.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuanganMatkulModel {
	private int idRuangan;
	private int idMatkul;
	
	
}
