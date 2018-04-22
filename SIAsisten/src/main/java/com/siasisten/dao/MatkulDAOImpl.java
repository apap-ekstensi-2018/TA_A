package com.siasisten.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

import com.siasisten.model.MatkulModel;

public class MatkulDAOImpl implements MatkulDAO {
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public MatkulModel selectMatkulbyId (int id) {
		MatkulModel matkul = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/matkul/view/id/" + id, 
				MatkulModel.class);
		return matkul;
	}
	
	@Override
	public MatkulModel selectMatkulbyKode (String kode) {
		MatkulModel matkul = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/matkul/view/kode/" + kode, 
				MatkulModel.class);
		return matkul;
	}
	
	public List<MatkulModel> selectAllMatkul () {
		MatkulModel[] listMatkul = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/matkul/viewall",
				MatkulModel[].class);
		List<MatkulModel> result = Arrays.asList(listMatkul);
		return result;
	}
	
	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}
}
