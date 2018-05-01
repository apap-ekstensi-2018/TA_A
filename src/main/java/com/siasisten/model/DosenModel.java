package com.siasisten.model;

import java.util.List;

import lombok.Builder;

public class DosenModel extends PegawaiModel {
	private List<MatkulModel> mataKuliahList;
	
	@Builder
	public DosenModel(int id, String nip, String nama, boolean isStaf, List<MatkulModel> mataKuliahList) {
		super(id, nip, nama, isStaf);
		this.setMataKuliahList(mataKuliahList);
	}

	/**
	 * @return the mataKuliahList
	 */
	public List<MatkulModel> getMataKuliahList() {
		return mataKuliahList;
	}

	/**
	 * @param mataKuliahList the mataKuliahList to set
	 */
	public void setMataKuliahList(List<MatkulModel> mataKuliahList) {
		this.mataKuliahList = mataKuliahList;
	}
}
