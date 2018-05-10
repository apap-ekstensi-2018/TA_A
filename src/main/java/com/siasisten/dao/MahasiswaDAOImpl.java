package com.siasisten.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten.model.MahasiswaModel;

@Service
public class MahasiswaDAOImpl implements MahasiswaDAO {
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public MahasiswaModel selectMahasiswabyId (int id) {
		MahasiswaModel mahasiswa = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/mahasiswa/view/id/" + id, 
				MahasiswaModel.class);
		return mahasiswa;
	}
	
	@Override
	public MahasiswaModel selectMahasiswabyNPM (String npm) {
		MahasiswaModel mahasiswa = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/mahasiswa/view/npm/" + npm, 
				MahasiswaModel.class);
		return mahasiswa;
	}
	
	public List<MahasiswaModel> selectAllMahasiswa () {
		MahasiswaModel[] listMahasiswa = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/mahasiswa/viewall",
				MahasiswaModel[].class);
		List<MahasiswaModel> result = Arrays.asList(listMahasiswa);
		return result;
	}
	
	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}
}
