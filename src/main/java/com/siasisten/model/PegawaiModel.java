package com.siasisten.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PegawaiModel {
	private int id;
	private String nip;
	private String nama;
	private boolean isStaf;
}
