package com.siasisten.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.siasisten.model.RuanganModel;

@Service
public class RuanganDAOImpl implements RuanganDAO {
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Override
	public RuanganModel selectRuanganbyId(int id) {
		RuanganModel ruangan = restTemplate.getForObject(
				"https://kelompok-b.herokuapp.com/api/ruang/view/" + id, 
				RuanganModel.class);
		return ruangan;
	}

}
