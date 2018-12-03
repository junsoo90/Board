package com.spring.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.Service.BoardService;
import com.spring.board.domain.BoardVO;

@Controller
@RequestMapping("/board/*") // http://localhost:8080/board 인 경우에 먼저 호출되는 클래스
public class BoardController {

	// 서비스 객체 (쿼리문 실행)
	@Inject
	private BoardService service;

	/*
	 * register.jsp 에 있는 post 방식의 form 태그에서 submit이 된 경우 호출 전달받은 게시물을 등록함(POST 방식)
	 */

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO board, Model model) throws Exception {

		service.regist(board);

		model.addAttribute("result", "registerOK");

		// return "/board/success";
		return "redirect:/board/listAll"; // 리다이렉트
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception {

		List<BoardVO> list = service.listAll(); // 서비스 객체의 listAll() 메소드 호출
		System.out.println(list.toString());
		model.addAttribute("list", list); // 조회결과를 model의 list속성으로 추가
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") Integer bno, Model model) throws Exception {

		BoardVO board = service.read(bno);
		model.addAttribute(board);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public String remove(@RequestParam("bno") Integer bno, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		rttr.addFlashAttribute("result", "removeOK");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateGET(@RequestParam("bno") Integer bno, Model model) throws Exception{
		
		BoardVO board = service.read(bno);
		model.addAttribute(board);
	}
    
    //실제로 게시물을 수정함
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(BoardVO board, RedirectAttributes rttr) throws Exception{
		
		service.modify(board);
		rttr.addFlashAttribute("result","saveOK");
		return "redirect:/board/read?bno="+board.getBno();
	}
}