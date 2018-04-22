package com.siasisten.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten.model.PegawaiModel;

@Service 
public class PegawaiDAOImpl implements PegawaiDAO {
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public PegawaiModel selectPegawaibyId (int id) {
		PegawaiModel pegawai = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/staf/view/id/" + id, 
				PegawaiModel.class);
		return pegawai;
	}
	
	@Override
	public PegawaiModel selectPegawaibyNIP (String nip) {
		PegawaiModel pegawai = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/staf/view/nip/" + nip, 
				PegawaiModel.class);
		return pegawai;
	}
	
	public List<PegawaiModel> selectAllPegawai () {
		PegawaiModel[] listPegawai = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/staf/viewall",
				PegawaiModel[].class);
		List<PegawaiModel> result = Arrays.asList(listPegawai);
		return result;
	}
	
	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}
}
