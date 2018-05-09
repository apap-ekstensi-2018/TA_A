package com.siasisten.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.siasisten.model.PengajuanModel;

@Mapper
public interface MahasiswaMapper {
	@Select("SELECT CASE WHEN COUNT(id) > 0 THEN 1 ELSE 0 END AS 'isAsdos' FROM `pengajuan` WHERE username_mahasiswa = #{npm} AND is_accepted = 1")
    boolean isAsdos(@Param("npm") String npm);
}
