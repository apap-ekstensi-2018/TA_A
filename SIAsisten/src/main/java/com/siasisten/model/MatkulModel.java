package com.siasisten.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatkulModel {
	private int id;
	private String kodeMatkul;
	private String namaMatkul;
}