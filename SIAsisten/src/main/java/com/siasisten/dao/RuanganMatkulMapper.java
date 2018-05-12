package com.siasisten.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


import com.siasisten.model.RuanganMatkulModel;

@Mapper
public interface RuanganMatkulMapper {
	@Select("select id_ruangan as idRuangan  FROM `ruangan_matkul` where id_matkul = #{idMatkul}")
	List<RuanganMatkulModel> selectRuanganbyIdMatkul (@Param("idMatkul") int idMatkul);
}
