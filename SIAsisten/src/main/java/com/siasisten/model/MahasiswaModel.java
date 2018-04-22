package com.siasisten.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MahasiswaModel {
	private int id;
	private String nama;
	private String npm;
	private List<MatkulModel> mataKuliahList;
}
