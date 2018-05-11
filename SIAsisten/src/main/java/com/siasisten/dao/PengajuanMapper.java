package com.siasisten.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.siasisten.model.PengajuanModel;

@Mapper
public interface PengajuanMapper {
	@Select("Select id_lowongan as idLowongan, username_mahasiswa as usernameMhs,is_accepted as isAccepted from pengajuan")
	List<PengajuanModel> selectAllPengajuan();
	
	@Select("Select id as id, id_lowongan as idLowongan, username_mahasiswa as usernameMhs, is_accepted as isAccepted from pengajuan where username_mahasiswa = #{usernameMhs}")
	List<PengajuanModel> selectAllPengajuanMhs(@Param("usernameMhs") String usernameMhs);
	
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
	
	@Select("select count(id) from pengajuan where id = #{id}")
	int countPengajuanById(@Param("id")int id);
	
	@Select("select count(id) from pengajuan where is_accepted = 1 and id = #{id}")
	int countDiterimaById(@Param("id")int id);
}
