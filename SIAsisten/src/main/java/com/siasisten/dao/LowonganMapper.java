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

	@Update("UPDATE `lowongan` SET `is_open`=#{isOpen},`jml_lowongan`=#{jmlLowongan} WHERE id=#{idMatkul}")
	void updateLowongan (LowonganModel lowongan);
		
	@Select("select id, id_matkul as idMatkul, is_open as isOpen, jml_lowongan as jmlLowongan FROM lowongan")
	List<LowonganModel> selectAllLowongan();
	
	@Select("select count(*) from lowongan")
	int countLowongan();
	
	@Select("select id, id_matkul as idMatkul, is_open as isOpen, jml_lowongan as jmlLowongan FROM lowongan where FIND_IN_SET(id_matkul, #{listIdMatkul})")
	List<LowonganModel> selectAllLowonganByDosen(@Param("listIdMatkul") String listIdMatkul);
}
