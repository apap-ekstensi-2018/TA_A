package com.siasisten.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Builder;

public class DosenModel extends PegawaiModel {
	@JsonProperty("mataKuliahList")
	private List<MatkulModel> mataKuliahList;
	
	@Builder
	@JsonCreator
	public DosenModel(@JsonProperty("id") int id, @JsonProperty("nip") String nip,@JsonProperty("nama") String nama) {
		super(id, nip, nama);
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
