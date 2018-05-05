package com.siasisten.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import com.siasisten.model.LowonganModel;

@Mapper
public interface LowonganMapper {
	@Insert("Insert into lowongan (id_matkul,is_open,jml_lowongan) values (#{idMatkul}, #{isOpen}, #{jmlLowongan})")
	void addLowongan(LowonganModel lowongan);
	
	@Delete("DELETE FROM `lowongan` WHERE id=#{idlowongan}")
	void deleteLowongan (@Param("idlowongan")int idlowongan); 

	@Select("select id, id_matkul as idMatkul, is_open as isOpen, jml_lowongan as jmlLowongan FROM lowongan where id = #{idlowongan}")
    LowonganModel selectLowonganbyID (@Param("idlowongan") int idlowongan);
	
	@Select("select count(id) from lowongan where id_matkul=#{id_matkul}")
	int cekLowongan(int id_matkul);

	@Update("UPDATE `lowongan` SET `is_open`=#{statusFixed},`jml_lowongan`=#{jml_slot} WHERE id=#{idlowongan}")
	void updateLowongan (@Param("idlowongan")int idlowongan, @Param("id_matkul")int id_matkul, @Param("statusFixed")int statusFixed, @Param("jml_slot")int jml_slot);
		
	@Select("select id, id_matkul as idMatkul, is_open as isOpen, jml_lowongan as jmlLowongan FROM lowongan")
	List<LowonganModel> selectAllLowongan();
}
