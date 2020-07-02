package fits.stu.edu.vn.qlsv.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;


import fits.stu.edu.vn.qlsv.domain.Sinhvien;
import fits.stu.edu.vn.qlsv.respo.SinhVienRepo;

@Controller
public class SinhVienController {
	@Autowired
	private SinhVienRepo sinhvienRepo;
	
	@GetMapping("/")
	public String showAll(Model model) {
		List<Sinhvien> dssv = sinhvienRepo.findAll();
		model.addAttribute("dssv", dssv);
		return "danhsachsinhvien.html";
	}
	@GetMapping("delete")
	public String delete(Model model, @RequestParam String mssv) {
		Optional<Sinhvien> optSv = sinhvienRepo.findById(mssv);
		
		if(optSv.isPresent())
			sinhvienRepo.delete(optSv.get());
		return "redirect:/";
	}
	@GetMapping("add")
	public String Add(@RequestParam String mssv,
			@RequestParam String hoten,
			@RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy")Date ngaysinh,
			@RequestParam int phai,
			@RequestParam float dtb) {
		Sinhvien sv = new Sinhvien();
		sv.setMssv(mssv);
		sv.setHoten(hoten);
		sv.setNgaysinh(ngaysinh);
		sv.setPhai(phai==1);
		sv.setDtb(dtb);
		sinhvienRepo.save(sv);
		return "redirect:/";
	}
}
