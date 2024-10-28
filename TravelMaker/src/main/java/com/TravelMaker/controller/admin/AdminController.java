package com.TravelMaker.controller.admin;

import com.TravelMaker.model.Recommend_TravelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.TravelMaker.model.LodgeDTO;
import com.TravelMaker.model.Lodge_Room_TypeDTO;
import com.TravelMaker.service.admin.AdminService;
import com.TravelMaker.service.lodge.LodgeService;
import com.TravelMaker.service.recommendTravel.Recommend_TravelService;

import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	
	@Autowired private LodgeService lodgeService;
	@Autowired private AdminService adminService;
	@Autowired private Recommend_TravelService recommend_travelService;
	
	/* admin페이지 이동 */
	@GetMapping("/admin")
	public void admin() {}
	
	
	/* 여행 추천 작성 페이지 이동 */
	@GetMapping("/recommendWrite")
	public void recommendWrite(){}
	
	/* 여행 추천  Form  DB 삽입  */
	@PostMapping("/recommendWrite")
	public String recommendWrite(Recommend_TravelDTO dto){
		int row = adminService.insertRecommend(dto);

		return "redirect:/Admin/admin";
	}

	/* 여행 추천 DB 삭제 */
	@GetMapping("/recommendDelete/{idx}")
	public String recommendDelete(@PathVariable ("idx") int idx){
		int row = adminService.deleteOneByIdx(idx);

		return "redirect:/Admin/admin";
	}
	
	/* 여행 추천 DB 수정페이지 */
	@GetMapping("/recommendModify/{idx}")
	public ModelAndView recommendModify(@PathVariable ("idx")int idx){
		ModelAndView mav = new ModelAndView("/Admin/recommendModify");
		Recommend_TravelDTO dto = recommend_travelService.getRecommend(idx);
		mav.addObject("dto", dto);

		return mav;
	}
	/* 여행 추천 Form 수정 DB삽입 */
	@PostMapping("/recommendModify/{idx}")
	public String recommendModify(Recommend_TravelDTO dto){
		int row = adminService.updateRecommend(dto);

		System.out.println(row + "행이 수정 되었습니다");

		return "redirect:/Admin/admin";
	}
	
	/* lodge 작성 페이지 이동 */
	@GetMapping("/lodgeWrite")
	public void lodgeWrite() {}
	
	/* lodge Form  DB 삽입  */
	@PostMapping("/actionLodgeWrite")
	public String lodgeWrite(LodgeDTO dto) {
		int row = adminService.insertLodge(dto);
		
		return "redirect:/Admin/admin";
	}

	
	@GetMapping("/lodgeUpdateOne/{idx}")
	public String deleteOnt(@PathVariable("idx") int idx) {
		int row = adminService.roomDelete(idx);
		int tmp = adminService.lodgeDelete(idx);
		return "redirect:/lodge/lodge_main";
		
	}
	
	@GetMapping("/lodgecorrectionOne/{idx}")
	public ModelAndView correction(@PathVariable("idx") int idx) {
		ModelAndView mav = new ModelAndView("Admin/lodgecorrection");
		LodgeDTO lodgeDTO = lodgeService.getLodge(idx);
		mav.addObject("lodgeDTO",lodgeDTO);
		return mav;
	}
	
	@PostMapping("/lodgecorrectionOne/{idx}")
	public ModelAndView lodgecorrectionOne(LodgeDTO dto) {
		ModelAndView mav = new ModelAndView("Admin/lodgecorrection");
		int tmp = adminService.lodgemodifyOne(dto);
		System.out.println(tmp != 0 ? "lodge수정 성공" : "lodge수정 실패");
		mav.addObject("tmp", tmp);
		return mav;
	}
	
	@GetMapping("/roomcorrection/{idx}")
	public ModelAndView roomcorrection(@PathVariable("idx") int idx) {
		ModelAndView mav = new ModelAndView("Admin/roomcorrection");
		Lodge_Room_TypeDTO roomDTO = lodgeService.getOneRoom(idx);
		mav.addObject("roomDTO",roomDTO);
		return mav;
	}
	
	@PostMapping("/roomcorrection/{idx}")
	public ModelAndView roomcorrection(Lodge_Room_TypeDTO dto) {
		ModelAndView mav = new ModelAndView("Admin/roomcorrection");
		int row = adminService.roomcorrection(dto);
		mav.addObject("row", row);
		return mav;
	}

	
	/* lodgeroom 작성 페이지 이동 */
	@GetMapping("/lodgeRoomWrite/{idx}")
	public ModelAndView lodgeRoomWrite(@PathVariable("idx") int idx) {
		ModelAndView mav = new ModelAndView("Admin/lodgeRoomWrite");
		LodgeDTO lodgeDTO = lodgeService.getLodge(idx);
		mav.addObject("lodgeDTO",lodgeDTO);
		return mav;
	}
	
	/* lodgeroom  DB 삽입  */
	@PostMapping("/lodgeRoomWrite/{idx}")
	public  String lodgeWrite(Lodge_Room_TypeDTO dto) {
		System.out.println(dto.getLodge_Idx());
		int row = adminService.insertLodgeRoom(dto);
		return "redirect:/lodge/LodgeRoom/" + dto.getLodge_Idx();
	
	

	}
	

}