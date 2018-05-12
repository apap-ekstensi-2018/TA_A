package com.siasisten.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.siasisten.model.LowonganModel;
import com.siasisten.model.PengajuanModel;

@Mapper
public interface PengajuanMapper {
	
	@Select("SELECT id AS id, id_lowongan AS idLowongan, username_mahasiswa AS usernameMhs, is_accepted AS isAccepted FROM `pengajuan` WHERE id = #{id}")
	PengajuanModel selectPengajuanById(@Param("id") int id);
	
	@Select("SELECT id AS id, id_lowongan AS idLowongan, username_mahasiswa AS usernameMhs, is_accepted AS isAccepted FROM `pengajuan` WHERE id_lowongan = #{idLowongan}")
	PengajuanModel selectPengajuanByIdLowongan(@Param("idLowongan") int idLowongan);
	
	@Delete("DELETE FROM `pengajuan` WHERE id=#{id}")
	void deletePengajuan(@Param("id")int id); 
	
	@Update("UPDATE `pengajuan` SET `is_accepted`=#{isAccepted} WHERE id=#{id}")
	void updatePengajuan (PengajuanModel pengajuan);
	
	@Select("select count(*) from pengajuan")
	int countPengajuan();
	
	@Select("select username_mahasiswa from pengajuan where username_mahasiswa=#{username_mahasiswa}")
	String cekPengajuan(String username_mahasiswa);
	
	@Insert("Insert into pengajuan (id_lowongan,username_mahasiswa,is_accepted) values (#{idLowongan}, #{usernameMhs}, 2)")
	void addPengajuan(PengajuanModel pengajuan);
}
