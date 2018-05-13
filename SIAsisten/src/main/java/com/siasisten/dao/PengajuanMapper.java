package com.siasisten.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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
	
	@Select("Select id as id, id_lowongan as idLowongan, username_mahasiswa as usernameMhs, is_accepted as isAccepted from pengajuan where id_lowongan = #{listIdLowongan}")
	List<PengajuanModel> selectAllPengajuanDosen(@Param("listIdLowongan") String listIdLowongan);
	
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
	
	@Select("select count(id) from pengajuan where id_lowongan = #{id}")
	int countPengajuanById(@Param("id")int id);
	
	@Select("select count(id) from pengajuan where is_accepted = 1 and id = #{id}")
	int countDiterimaById(@Param("id")int id);
	
	@Select("SELECT CASE WHEN COUNT(id) > 0 THEN 1 ELSE 0 END AS 'isRegister' FROM `pengajuan` WHERE username_mahasiswa = #{username} AND id_lowongan = #{idLowongan}")
    int isRegister(@Param("username") String username, @Param("idLowongan") int idLowongan);

	@Select("select CONCAT('',cast(id_lowongan as char), username_mahasiswa) as gabungan from pengajuan where username_mahasiswa = #{username_mahasiswa} and id_lowongan = #{id_lowongan}")
	String cekPengajuan(@Param("username_mahasiswa") String username_mahasiswa, @Param("id_lowongan")int id_lowongan);
	
	@Insert("Insert into pengajuan (id_lowongan,username_mahasiswa,is_accepted) values (#{idLowongan}, #{usernameMhs}, 2)")
	void addPengajuan(PengajuanModel pengajuan);
}
