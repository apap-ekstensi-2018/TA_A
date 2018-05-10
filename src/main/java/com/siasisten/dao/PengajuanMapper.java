package com.siasisten.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Delete;

import com.siasisten.model.LowonganModel;
import com.siasisten.model.PengajuanModel;

@Mapper
public interface PengajuanMapper {
	@Select("select id_lowongan as idlowongan, username_mahasiswa as usernameMhs, is_accepted as isAccepted FROM pengajuan where id = #{id}")
    PengajuanModel selectPengajuanbyID (@Param("id") int id);
	
}
