package com.siasisten.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.siasisten.model.LowonganModel;

@Mapper
public interface LowonganMapper {
	@Insert("Insert into lowongan (id_matkul,is_open,jml_lowongan) values (#{idMatkul}, #{isOpen}, #{jmlLowongan})")
	void addLowongan(LowonganModel lowongan);

	@Select("select id, id_matkul as idMatkul, is_open as isOpen, jml_lowongan as jmlLowongan FROM lowongan where id = #{idlowongan}")
    LowonganModel selectLowonganbyID (@Param("idlowongan") int idlowongan);
	
	@Update("UPDATE `lowongan` SET `is_open`=#{isOpen},`jml_lowongan`=#{jmlLowongan} WHERE id=#{id}")
	void updateLowongan (LowonganModel student);
}
