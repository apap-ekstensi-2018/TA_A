package com.siasisten.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LowonganModel {
	private int id;
	private int idMatkul;
	private boolean isOpen;
	private int jmlLowongan;
}
