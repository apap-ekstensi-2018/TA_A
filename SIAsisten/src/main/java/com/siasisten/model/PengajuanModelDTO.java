package com.siasisten.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PengajuanModelDTO {
	private int id;
	private String namaDosen;
	private String namaMatkul;
	private int statusLowongan;
	private int jumlahLowongan;
}
