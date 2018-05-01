package com.siasisten.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.siasisten.model.LowonganModel;

@Mapper
public interface LowonganMapper {

	@Select("SELECT id AS id, id_matkul AS idMatkul, is_open AS isOpen, jml_lowongan AS jmlLowongan FROM `lowongan` WHERE id = #{id_lowongan}")
	LowonganModel selectLowonganById (@Param("id_lowongan") int id_lowongan);
	
	@Select("UPDATE `lowongan` SET `is_open`=#{statusFixed},`jml_lowongan`=#{jml_slot} WHERE id=#{idlowongan}")
	void updateLowongan (@Param("idlowongan")int idlowongan, @Param("id_matkul")int id_matkul, @Param("statusFixed")boolean statusFixed, @Param("jml_slot")int jml_slot);
	
}
