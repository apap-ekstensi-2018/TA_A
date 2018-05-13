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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.siasisten.dao.DosenDAO;
import com.siasisten.dao.MahasiswaDAO;
import com.siasisten.dao.MatkulDAO;
import com.siasisten.dao.RuanganDAO;
import com.siasisten.model.DosenModel;
import com.siasisten.model.LowonganModel;
import com.siasisten.model.LowonganModelDTO;
import com.siasisten.model.MahasiswaModel;
import com.siasisten.model.MatkulModel;
import com.siasisten.model.RuanganMatkulModel;
import com.siasisten.model.RuanganModel;
import com.siasisten.service.LowonganService;
import com.siasisten.service.PengajuanService;
import com.siasisten.service.RuanganMatkulService;


@Controller
public class LowonganController {

	@Autowired
	LowonganService lowonganDAO;
	
	@Autowired
	MatkulDAO matkulDao;
	
	@Autowired
	RuanganDAO ruanganDao;
	
	@Autowired
	RuanganMatkulService ruanganMatkulDAO;
	
	@Autowired
	DosenDAO dosenDAO;
	
	@Autowired
	PengajuanService pengajuanDAO;
	
	@Autowired
	MahasiswaDAO mahasiswaDAO;
	
	@RequestMapping("/lowongan")
    public String index (Model model)
    {
    	model.addAttribute("pageTitle", "Lowongan");
        return "home";
    }
	

	@RequestMapping("/lowongan/tambah")
	public String tambahLowongan(Model model, Authentication auth, @ModelAttribute("lowongan") LowonganModel lowongan)
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
		
		DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
		model.addAttribute("namaUser", dosen.getNama());
		List<MatkulModel> matkul = dosen.getMataKuliahList();
		System.out.println(userId);
		System.out.println(matkul);
		//List<MatkulModel> matkul = matkulDao.selectAllMatkul();
		model.addAttribute("matkul", matkul);
		model.addAttribute("pageTitle", "Tambah Lowongan");
		if(lowongan.getIdMatkul()==0) {
			return "form-addLowongan";
		}
		else {
			if (isExistLowongan(lowongan.getIdMatkul())> 0) {
				return "failed-add";
			}
			else {
				model.addAttribute("idMatkul", lowongan.getIdMatkul());
				model.addAttribute("message", "Lowongan dengan Mata Kuliah" + lowongan.getIdMatkul() + "berhasil ditambahkan");
				lowonganDAO.addLowongan(lowongan);
				return "success-add";
			}
		}
	}
	
	public int isExistLowongan(int idMatkul) {
		return lowonganDAO.cekLowongan(idMatkul);
	}
	
	@RequestMapping("/lowongan/view/{idlowongan}")
    public String cariLowongan (Model model, Authentication auth,
            @PathVariable(value = "idlowongan") int idlowongan)
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
		
		
		LowonganModel lm = lowonganDAO.selectLowonganbyID(idlowongan);
		MatkulModel mk = matkulDao.selectMatkulbyId(lm.getIdMatkul());

		int isopen = lm.getIsOpen();

		List<RuanganMatkulModel> rm = ruanganMatkulDAO.selectRuanganbyIdMatkul(mk.getId());
		//String idR = String.valueOf(rm.get(0));
		List<RuanganModel> ruLowongan = new ArrayList<>();
		for (RuanganMatkulModel ruang : rm) {
			RuanganModel item = ruanganDao.selectRuanganbyId(ruang.getIdRuangan());
			ruLowongan.add(item);
		}
		
		
		
		model.addAttribute("tittle", "Cari Lowongan");
	    	model.addAttribute("lm", lm);
	    	model.addAttribute("isopen",isopen);
	    	model.addAttribute("matkul", mk);
	    	model.addAttribute("ruangan", ruLowongan);
	    	System.out.println("id ruangan"+ruLowongan);
	    	model.addAttribute("pageTitle", "View Lowongan");
	    return "view";
    }
	
	@RequestMapping("/lowongan/ubah/{id_lowongan}")
	public String lowonganUbah (Model model, Authentication auth, @PathVariable(value = "id_lowongan", required = false) int id_lowongan) 
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
		
		LowonganModel lowongan = lowonganDAO.selectLowonganbyID(id_lowongan);
		MatkulModel matkul = matkulDao.selectMatkulbyId(lowongan.getIdMatkul());
		model.addAttribute("matkul", matkul);
		model.addAttribute("lowongan", lowongan);
		System.out.println(lowongan.getIsOpen());
		model.addAttribute("pageTitle", "Ubah Lowongan");
		model.addAttribute("id_lowongan", id_lowongan);
		return "ubah-lowongan";
	}
	
	@PostMapping("/lowongan/ubah/submit")
	public String ubahSubmit (Model model, Authentication auth, @RequestParam(value = "matakuliah", required = false) String matakuliah,
										   @RequestParam(value = "isOpen", required = false) int isOpen,
										   @RequestParam(value = "jml_slot", required = false) int jml_slot,
										   @RequestParam(value = "id_lowongan", required = false) int idLowongan,
										   @RequestParam(value = "id_matkul", required = false) int id_matkul) 
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
		
		LowonganModel lowongan = new LowonganModel(idLowongan, id_matkul, isOpen, jml_slot);
		lowonganDAO.updateLowongan(lowongan);
		model.addAttribute("message", "Lowongan dengan id " + idLowongan + " , mata kuliah " + matakuliah + " berhasil diubah");
		return "success-update";
	}
	
	@RequestMapping("/lowongan/hapus/{id_lowongan}")
    public String deleteLowongan (Model model, Authentication auth, @PathVariable(value = "id_lowongan", required = false) int idlowongan)
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
		
		LowonganModel lowongan = lowonganDAO.selectLowonganbyID(idlowongan);
        if (lowongan != null) {
        	lowonganDAO.deleteLowongan(idlowongan);
        	model.addAttribute("message", "Lowongan dengan id " + lowongan.getId() + " berhasil dihapus");
            return "success-delete";
        } else {
            model.addAttribute ("idlowongan", idlowongan);
            return "not-found";
        }

    }
	
	@RequestMapping("/lowongan/viewall")
    public String cariSemuaLowongan (Model model)
    {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String userId = auth.getName();
			String listIdMatkul = "";
			Collection<? extends GrantedAuthority> authorities
		     = auth.getAuthorities();
			List<String> roles = new ArrayList<String>();
			for (GrantedAuthority a : authorities) {
		        roles.add(a.getAuthority());
		    }
			
			List<LowonganModelDTO> allLowonganDTO = new ArrayList<>();

			String roleUser = roles.get(0);
			//System.out.println(roleUser);
			//System.out.println(roleUser.substring(5, 10));
			model.addAttribute("role",roleUser.substring(5, roleUser.length()));

			if (roles.contains("ROLE_dosen"))
			{
				DosenModel dosen = dosenDAO.selectDosenbyNIP(userId);
				model.addAttribute("namaUser", dosen.getNama());
				List<MatkulModel> matkulDosen = dosen.getMataKuliahList();
				System.out.println(matkulDosen);
				//List<String> idList = new ArrayList<>();
				
				listIdMatkul = matkulDosen.stream()
						.map(p -> String.valueOf(p.getId()))
						.collect(Collectors.joining(","));
				
				List<LowonganModel> allLowongan = lowonganDAO.selectAllLowonganByDosen(listIdMatkul);
				System.out.println(listIdMatkul);
				model.addAttribute("allLowongan", allLowongan);
				System.out.println(lowonganDAO.selectAllLowonganByDosen(listIdMatkul));
				for(LowonganModel lMod : allLowongan) {
					LowonganModelDTO lDto = new LowonganModelDTO();
					lDto.setId(lMod.getId());
					MatkulModel mMod = matkulDao.selectMatkulbyId(lMod.getIdMatkul());
					lDto.setNamaMatkul(mMod.getNamaMatkul());
					System.out.println(mMod.getNamaMatkul());
					lDto.setIsOpen(lMod.getIsOpen());
					lDto.setJmlLowongan(lMod.getJmlLowongan());
					allLowonganDTO.add(lDto);
				}
			}
			else if (roles.contains("ROLE_mahasiswa"))
			{
				List<LowonganModel> allLowongan = lowonganDAO.selectAllLowongan();
				
				model.addAttribute("allLowongan", allLowongan);
				
				MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswabyNPM(userId);
				model.addAttribute("namaUser", mahasiswa.getNama());
				for(LowonganModel lMod : allLowongan) {
					LowonganModelDTO lDto = new LowonganModelDTO();
					lDto.setId(lMod.getId());
					MatkulModel mMod = matkulDao.selectMatkulbyId(lMod.getIdMatkul());
					int isRegister = pengajuanDAO.isRegister(userId, lMod.getId());
					lDto.setNamaMatkul(mMod.getNamaMatkul());
					lDto.setIsOpen(lMod.getIsOpen());
					lDto.setJmlLowongan(lMod.getJmlLowongan());
					lDto.setIsRegister(isRegister);
					allLowonganDTO.add(lDto);
				}
			}
			
			model.addAttribute("listIdMatkul", listIdMatkul);
			model.addAttribute("allLowonganDTO", allLowonganDTO);
			System.out.println(allLowonganDTO);
			model.addAttribute("pageTitle", "View All Lowongan");
		    return "viewall";
    }
}
