package com.siasisten.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten.dao.DosenDAO;
import com.siasisten.dao.MahasiswaDAO;
import com.siasisten.dao.MatkulDAO;
import com.siasisten.model.DosenModel;
import com.siasisten.model.LowonganModel;
import com.siasisten.model.LowonganModelDTO;
import com.siasisten.model.MahasiswaModel;
import com.siasisten.model.MatkulModel;
import com.siasisten.model.PengajuanModel;
import com.siasisten.model.PengajuanModelDTO;
import com.siasisten.service.LowonganService;
import com.siasisten.service.MahasiswaService;
import com.siasisten.service.PengajuanService;
import com.siasisten.service.RuanganMatkulService;

@Controller
public class PengajuanController {
	
	@Autowired
	MahasiswaService mhsDAO;
	
	@Autowired
	PengajuanService pengajuanDAO;
	
	@Autowired
	LowonganService lowonganDAO;
	
	@Autowired
	RuanganMatkulService ruanganMatkulDAO;
	
	@Autowired
	MatkulDAO matkulDAO;
	
	@Autowired
	DosenDAO dosenDAO;
	
	@Autowired
	MahasiswaDAO mahasiswaDAO;
		
	@RequestMapping("/pengajuan/view/{id}")
    public String lihatPengajuan(Model model, Authentication auth, @PathVariable(value = "id") int id)
    {	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		PengajuanModel pengajuan = pengajuanDAO.selectPengajuanById(id);
		if(pengajuan != null) {
			LowonganModel lowongan = lowonganDAO.selectLowonganbyID(pengajuan.getIdLowongan());
			MatkulModel matkul = matkulDAO.selectMatkulbyId(lowongan.getIdMatkul());	
			MahasiswaModel mahasiswa = mhsDAO.selectMahasiswabyNPM(pengajuan.getUsernameMhs());
			
		    model.addAttribute("pengajuan", pengajuan);
		   	model.addAttribute("matkul", matkul);
		   	model.addAttribute("mahasiswa", mahasiswa);
		   	System.out.println(pengajuan.getIsAccepted());
		   	model.addAttribute("pageTitle", "Lihat Pengajuan");
		    return "view-pengajuan";
		}
		else {
			model.addAttribute("idlowongan", id);
			return "not-found";
		}
    }
	
	@PostMapping("/pengajuan/hapus/{id_lowongan}")
	public String hapusPengajuanPost(@PathVariable(value = "id_lowongan", required = false) int id_lowongan, Authentication auth, Model model) 
	{	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		PengajuanModel pengajuan = pengajuanDAO.selectPengajuanById(id_lowongan);
		if (pengajuan.getIsAccepted()==1){
			return "failed-delete";
		}else {
			pengajuanDAO.deletePengajuan(id_lowongan);
			return "success-delete-pengajuan";
		}
		
		
	}
	@RequestMapping("/pengajuan/hapus/{id_lowongan}")
	public String hapusPengajuan(@PathVariable(value = "id_lowongan", required = false) int id_lowongan, Authentication auth, Model model) 
	{	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		PengajuanModel pengajuan = pengajuanDAO.selectPengajuanById(id_lowongan);
		if (pengajuan.getIsAccepted()==1){
			return "failed-delete";
		}else {
			pengajuanDAO.deletePengajuan(id_lowongan);
			return "success-delete-pengajuan";
		}
		
	}
	
	/*Menghapus dari URL*/
	@RequestMapping("/pengajuan/hapus/{id_lowongan}")
	public String deletePengajuan(@PathVariable(value = "id_lowongan") int id_lowongan, Authentication auth, Model model) 
	{	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		pengajuanDAO.deletePengajuan(id_lowongan);
		return "success-delete-pengajuan";
	}
	
	@RequestMapping("/pengajuan/review/{id_pengajuan}")
	public String pengajuanUbah (Model model, @PathVariable(value = "id_pengajuan", required = false) int id_pengajuan, Authentication auth) 
	{	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		PengajuanModel pengajuan = pengajuanDAO.selectPengajuanById(id_pengajuan);
		LowonganModel lowongan = lowonganDAO.selectLowonganbyID(pengajuan.getIdLowongan());
		MatkulModel matkul = matkulDAO.selectMatkulbyId(lowongan.getIdMatkul());	
		MahasiswaModel mahasiswa = mhsDAO.selectMahasiswabyNPM(pengajuan.getUsernameMhs());
		model.addAttribute("pengajuan", pengajuan);
	   	model.addAttribute("matkul", matkul);
	   	model.addAttribute("mahasiswa", mahasiswa);
	   	System.out.println(pengajuan.getIsAccepted());
	   	model.addAttribute("pageTitle", "Review Pengajuan");
		return "ubah-pengajuan";
	}
	
	@PostMapping("/pengajuan/review/submit")
	public String ubahSubmit (Model model, Authentication auth, @RequestParam(value = "id", required = false) int id,
										   @RequestParam(value = "idLowongan", required = false) int idLowongan,
										   @RequestParam(value = "usernameMhs", required = false) String usernameMhs,
										   @RequestParam(value = "isAccepted", required = false) int isAccepted) 
	{
		
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		
		PengajuanModel pengajuan = new PengajuanModel(id, idLowongan, usernameMhs, isAccepted);
		String status="";
		if (pengajuan.getIsAccepted()==0) {
			status ="Ditolak";
		}else {
			status ="Diterima";
		}
		pengajuanDAO.updatePengajuan(pengajuan);
		model.addAttribute("message", "Lowongan dengan id " + idLowongan + " , NPM " + usernameMhs + "Dengan Status " +status + " berhasil diubah");
		return "success-update";
	}
	
	@RequestMapping("/pengajuan/tambah")
	public String tambahPengajuan(Model model, Authentication auth)
	{
		// get role and name
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		
		// get NPM login
		String userId = auth.getName();
		
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		List<LowonganModelDTO> allLowonganDTO = new ArrayList<>();
		List<LowonganModel> allLowongan = lowonganDAO.selectAllLowongan();
		model.addAttribute("allLowongan", allLowongan);
		for(LowonganModel lMod : allLowongan) {
			LowonganModelDTO lDto = new LowonganModelDTO();
			lDto.setId(lMod.getId());
			MatkulModel mMod = matkulDAO.selectMatkulbyId(lMod.getIdMatkul());
			mMod.getId();
			lDto.setNamaMatkul(mMod.getNamaMatkul());
			lDto.setJmlLowongan(lMod.getJmlLowongan());
			if(lMod.getIsOpen()==1) {
				lDto.setIsOpen(lMod.getIsOpen());
				allLowonganDTO.add(lDto);
			}	
		}
		
		System.out.println(userId);
		model.addAttribute("allLowonganDTO", allLowonganDTO);
		model.addAttribute("pageTitle", "Ajukan Pengajuan");
		
		return "form-addPengajuan";
	}
	
	@RequestMapping("/pengajuan/tambah/submit")
	public String tambahSubmit (Model model, Authentication auth, @RequestParam(value = "idLowongan", required = false) int idLowongan)
			 
	{	
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		}
			
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		
		// get NPM login
		String userId = auth.getName();
		
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		System.out.println("id="+idLowongan);
		
		int id = 0, isAccepted = 0;
		
		if ((isExistPengajuan(userId, idLowongan)==null)) {
			PengajuanModel pengajuan = new PengajuanModel(id, idLowongan, userId, isAccepted);
			pengajuanDAO.addPengajuan(pengajuan);
			model.addAttribute("message", "Pengajuan Asisten dengan id " + idLowongan + " , NPM" + userId + " berhasil ditambahkan");
			return "success-addPengajuan";
		}else{
			return "failed-addPengajuan";
		}
	}
	
	public String isExistPengajuan(String username_mahasiswa, int id_lowongan) {
		return pengajuanDAO.cekPengajuan(username_mahasiswa, id_lowongan);
	}
	
	
	@RequestMapping("/pengajuan/viewall")
	public String viewAllPengajuan(Model model) {
		
			
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		Collection<? extends GrantedAuthority> authorities
	     = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
	        roles.add(a.getAuthority());
	    }
		
		
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		String userId = auth.getName();
		if (roles.contains("ROLE_dosen"))
		{
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			model.addAttribute("namaUser", dosen.getNama());
		}else {
			MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswa.getNama());
		}
		
		
		//String userId = auth.getName();
		int jmlPengajuan = 0;
		int jmlDiterima = 0;
		
		List<PengajuanModelDTO> AllpengajuanDTO = new ArrayList<>();
		List<PengajuanModel> Allpengajuan = new ArrayList<>();
		
		if (roles.contains("ROLE_mahasiswa")) {
			
			Allpengajuan = pengajuanDAO.selectAllPengajuanMhs(userId);

			for (PengajuanModel peng : Allpengajuan) {
				List<String> nmDosen = new ArrayList<String>();
				PengajuanModelDTO pmd = new PengajuanModelDTO();
				
				LowonganModel lMod = lowonganDAO.selectLowonganbyID(peng.getIdLowongan());
				MatkulModel mMod = matkulDAO.selectMatkulbyId(lMod.getIdMatkul());
				jmlPengajuan = pengajuanDAO.countPengajuanById(peng.getIdLowongan());
				jmlDiterima = pengajuanDAO.countDiterimaById(peng.getIdLowongan());
				
				for(int i=0; i<mMod.getDosenList().size(); i++) {
					nmDosen.add(mMod.getDosenList().get(i).getNama());
				}
				
				pmd.setId(peng.getId());
				pmd.setIdLowongan(peng.getIdLowongan());
				pmd.setIdMatkul(lMod.getIdMatkul());
				pmd.setNamaMatkul(mMod.getNamaMatkul());
				pmd.setKodeMatkul(mMod.getKodeMatkul());
				pmd.setNamaDosen(String.join(",", nmDosen));
				pmd.setStatusLowongan(lMod.getIsOpen());
				pmd.setStatusPengajuan(peng.getIsAccepted());
				pmd.setJumlahLowongan(lMod.getJmlLowongan());
				pmd.setJumlahPengajuan(jmlPengajuan);
				pmd.setJumlahDiterima(jmlDiterima);
				
				AllpengajuanDTO.add(pmd);
			}
			
			model.addAttribute("viewMode", "mahasiswa");
		}
		else if (roles.contains("ROLE_dosen")) {
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			List<MatkulModel> matkulDosen = dosen.getMataKuliahList();
			String listIdMatkul = matkulDosen.stream()
					.map(p -> String.valueOf(p.getId()))
					.collect(Collectors.joining(","));
			
			List<LowonganModel> allLowongan = lowonganDAO.selectAllLowonganByDosen(listIdMatkul);
			List<String> listIdLowongan = new ArrayList<String>();
			for(LowonganModel lMod : allLowongan) {
				listIdLowongan.add(Integer.toString(lMod.getId()));
			}
			
			Allpengajuan = pengajuanDAO.selectAllPengajuanDosen(String.join(",", listIdLowongan));
			
			for (PengajuanModel peng : Allpengajuan) {
				List<String> nmDosen = new ArrayList<String>();
				PengajuanModelDTO pmd = new PengajuanModelDTO();
				
				LowonganModel lMod = lowonganDAO.selectLowonganbyID(peng.getIdLowongan());
				MatkulModel mMod = matkulDAO.selectMatkulbyId(lMod.getIdMatkul());
				MahasiswaModel mhs = mhsDAO.selectMahasiswabyNPM(peng.getUsernameMhs());
				jmlPengajuan = pengajuanDAO.countPengajuanById(peng.getIdLowongan());
				jmlDiterima = pengajuanDAO.countDiterimaById(peng.getIdLowongan());
				
				for(int i=0; i<mMod.getDosenList().size(); i++) {
					nmDosen.add(mMod.getDosenList().get(i).getNama());
				}
				
				pmd.setId(peng.getId());
				pmd.setIdLowongan(peng.getIdLowongan());
				pmd.setIdMatkul(lMod.getIdMatkul());
				pmd.setNamaMatkul(mMod.getNamaMatkul());
				pmd.setKodeMatkul(mMod.getKodeMatkul());
				pmd.setNamaDosen(String.join(",", nmDosen));
				pmd.setStatusLowongan(lMod.getIsOpen());
				pmd.setNamaMhs(mhs.getNama());
				pmd.setNpmMhs(mhs.getNpm());
				pmd.setStatusPengajuan(peng.getIsAccepted());
				pmd.setJumlahLowongan(lMod.getJmlLowongan());
				pmd.setJumlahPengajuan(jmlPengajuan);
				pmd.setJumlahDiterima(jmlDiterima);
				
				AllpengajuanDTO.add(pmd);
			}
			
			model.addAttribute("viewMode", "dosen");
		}
		
		model.addAttribute("Allpengajuan", Allpengajuan);
		model.addAttribute("pageTitle", "Lihat Seluruh Pengajuan");
		model.addAttribute("AllpengajuanDTO",AllpengajuanDTO);
		return "viewall-pengajuan";
	}
	
	@RequestMapping(value="/mata-kuliah/{idMatkul}", method = RequestMethod.GET)
	public String viewAllAsisten(Model model, 
			@PathVariable("idMatkul") String idMatkul) {
		model.addAttribute("pageTitle", "Lihat Seluruh Asisten");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		List<String> roles = getUserRoles(auth);
		List<String> listMatkul = new ArrayList<>();
		String roleUser = roles.get(0);
		System.out.println(roleUser);
		System.out.println(roleUser.substring(5, 10));
		model.addAttribute("role",roleUser.substring(5, roleUser.length()));
		
		if (roles.contains("ROLE_dosen")) {
			DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
			List<MatkulModel> matkulDosen = dosen.getMataKuliahList();
			for (MatkulModel matkul : matkulDosen) {
				int idMatkulDosen = matkul.getId();
				listMatkul.add(Integer.toString(idMatkulDosen));
			}
			model.addAttribute("namaUser", dosen.getNama());
		}
		else if (roles.contains("ROLE_mahasiswa")) {
			listMatkul = pengajuanDAO.selectAllMatkulAsistenByUsername(userId);
			MahasiswaModel mahasiswaU = mahasiswaDAO.selectMahasiswabyNPM(userId);
			model.addAttribute("namaUser", mahasiswaU.getNama());
		}
		
		if (listMatkul.contains(idMatkul)) {
			MatkulModel mMod = matkulDAO.selectMatkulbyId(Integer.parseInt(idMatkul));
			List<String> listNPM = pengajuanDAO.selectPengajuanByIdMatkul(Integer.parseInt(idMatkul));
			List<MahasiswaModel> listMhs = new ArrayList<>();
			for (String npm : listNPM) {
				MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(npm);
				listMhs.add(mahasiswa);
			}
			
			model.addAttribute("matkul", mMod);
			model.addAttribute("mahasiswa", listMhs);
			
			
			return "viewall-asisten";
			
		}
		else {
			return "/error/403";
		}
	}
	
	public List<String> getUserRoles (Authentication auth) {
		Collection<? extends GrantedAuthority> authorities
	     = auth.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
	        roles.add(a.getAuthority());
	    }
		
		return roles;
	}
}
