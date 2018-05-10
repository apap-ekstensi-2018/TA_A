package com.siasisten.dao;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siasisten.model.DosenModel;

@Service
public class DosenDAOImpl implements DosenDAO {
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public DosenModel selectDosenbyId (int id) {
		DosenModel dosen = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/dosen/view/id/" + id, 
				DosenModel.class);
		return dosen;
	}
	
	@Override
	public DosenModel selectDosenbyNIP (String nip) {
		DosenModel dosen = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/dosen/view/nip/" + nip, 
				DosenModel.class);
		return dosen;
	}
	
	@Override
	public List<DosenModel> selectAllDosen () {
		DosenModel[] listDosen = restTemplate.getForObject(
				"https://apap-fasilkom.herokuapp.com/api/dosen/viewall",
				DosenModel[].class);
		List<DosenModel> result = Arrays.asList(listDosen);
		return result;
	}
	
	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder) {
		return builder.build();
	}
}
