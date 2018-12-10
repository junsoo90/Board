package com.board.controller;

import java.io.File;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
//import org.slf4j.logger;
//import org.slf4j.loggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.VO.BoardVO;
import com.board.VO.Criteria;
import com.board.VO.PageMaker;
import com.board.VO.SearchCriteria;
import com.board.service.BoardService;
import com.mysql.cj.util.StringUtils;

@Controller

@RequestMapping("/board/*")
public class BoardController {

	// private static final //logger //logger =
	// //loggerFactory.get//logger(BoardController.class);

	@Inject
	BoardService service;

	// @Inject
	// ReplyService RepService;

	MultipartFile files;

	@RequestMapping("/")
	public void test() {
		System.out.println("ASdfasdfasdfsdfasdf");
		;
	}

	// 占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윪占쎄탾占쎈쐻占쎈윞占쎈렰 get
	@RequestMapping(value = "/testResult", method = RequestMethod.GET)
	public void GettestResult() {

		System.out.println("in test.jsp");
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void Gettest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in test.jsp");
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String Posttest() {

		return "home";
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public void getWrite(HttpSession session, Model model) throws Exception {
		// logger.info("get write");

		Object loginInfo = session.getAttribute("member");

		if (loginInfo == null) {
			model.addAttribute("msg", false);
		}

	}

	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String fileUpload(BoardVO board, @RequestParam MultipartFile files, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(request.getParameter("title"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		board.setPasswd(request.getParameter("passwd"));
		board.setWriter(request.getParameter("writer"));

		// logger.info("file upload");

		service.write(board, files);

		return "redirect:/board/listSearch";

	}

	@RequestMapping(value = "/fileDown/{bno}")
	private void fileDown(@PathVariable int bno, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("filedown in");

		System.out.println("bno = " + request.getParameter("bno"));
		// int bno = Integer.parseInt(request.getParameter("bno"));
		BoardVO vo = service.boardInfo(bno);

		String fileName = vo.getFilename();
		String fileurl = vo.getFileurl();
		String fileoriname = vo.getFileOriname();

		String resultfiledown = fileurl + fileName;
		System.out.println("resultfiledown = " + resultfiledown);
		byte fileByte[] = FileUtils.readFileToByteArray(new File(resultfiledown));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(fileoriname, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();

	}

	// 占쎈섀占쎈Ŋ�굲 癲ル슢�뀈泳�戮⑤뭄�뜝占�
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		// logger.info("get list");
		System.out.println("get list");
		List<BoardVO> list = service.list();

		model.addAttribute("list", list);
	}

	// 占쎈섀占쎈Ŋ�굲 占쎈뙑�⑤슦占싼띿삕占쎈쨧
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public Model getRead(@RequestParam("bno") int bno, HttpServletRequest request,
			@ModelAttribute("scri") SearchCriteria scri, Model model, HttpServletResponse response) throws Exception {
		// logger.info("get read");

		Cookie cookies[] = request.getCookies();
		Map mapcookie = new HashMap();

		if (request.getCookies() != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie temp = cookies[i];
				mapcookie.put(temp.getName(), temp.getValue());
			}
			System.out.println("�뜎醫뤾텕 占쎈씨占쎈뼣");
		}

		String cookie_viewcnt = (String) mapcookie.get("viewcnt");
		String new_cookie_viewcnt = "|" + bno;

		if (StringUtils.indexOfIgnoreCase(cookie_viewcnt, new_cookie_viewcnt) == -1) {

			Cookie cookie = new Cookie("viewcnt", cookie_viewcnt + new_cookie_viewcnt);

			response.addCookie(cookie);

		}

		System.out.println("cookie = " + request.getCookies());
		System.out.println("bno = " + bno);

		BoardVO vo = service.read(bno);

		System.out.println("fileoriname = " + vo.getFileOriname());
		System.out.println("fileurl = " + vo.getFileurl());

		model.addAttribute("read", vo);
		model.addAttribute("scri", scri);

		// List<ReplyVO> repList = RepService.readReply(bno);
		// model.addAttribute("repList", repList);
		System.out.println("read");
		return model;
	}

	// 占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윥占쎈묄 占쎈쐻占쎈윪占쎌젳
	@RequestMapping(value = "/modify")
	public void getModify(@RequestParam("bno") int bno, Model model,
			@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		// logger.info("get modify");
		System.out.println("get modify");

		BoardVO board = service.boardInfo(bno);

		System.out.println("bno = " + bno);
		System.out.println(board.getTitle());
		model.addAttribute("modify", board);
		model.addAttribute("scri", scri);
		System.out.println("get ok");

	}

	@RequestMapping(value = "/fileDeleteCheck", method = RequestMethod.GET)
	private String POSTFileDeleteCheck(BoardVO vo, @RequestParam("bno") int bno,
			@RequestParam("inputpasswd") String inputpasswd, 
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {

		System.out.println("file delete check");
		System.out.println("input passwd = " + inputpasswd);

		rttr.addAttribute("bno", bno);
		BoardVO board = service.boardInfo(bno);
		// service.fileDelete(board);

		String filename = board.getFilename();
		System.out.println("bno = " + bno);
		System.out.println("board.getfilename = " + board.getFilename());

		String fileurl = board.getFileurl();

		if (board.getPasswd().equals(inputpasswd)) {
			System.out.println("筌띿쉸釉�占쎈선");
			File file = new File(fileurl + filename);
			service.fileDelete(bno);
			if (file.exists() || filename != null || filename != "") {
				if (file.delete()) {
					System.out.println("占쎈솁占쎌뵬占쎄텣占쎌젫 占쎄쉐�⑨옙");
				} else {
					System.out.println("占쎈솁占쎌뵬占쎄텣占쎌젫 占쎈뼄占쎈솭");
				}
			} else {
				System.out.println("占쎈솁占쎌뵬占쎌뵠 鈺곕똻�삺占쎈릭筌욑옙 占쎈륫占쎈뮸占쎈빍占쎈뼄.");
			}

			System.out.println("board.getfilename = " + board.getFilename());

		} else {
			System.out.println("占쏙옙占쎌죬占쎈선");

			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = response.getWriter();

			writer.println("<script> alert('�뜮袁⑨옙甕곕뜇�깈 �겫�뜆�뵬燁삼옙');  ");
			writer.println("history.back();</script>");
			writer.flush();
		}

		return "redirect:/board/read?bno={bno}";
	}

	@RequestMapping(value = "/passwdCheck")
	private String GetpasswdCheck(BoardVO vo,// @RequestParam("bno") int bno,
			@RequestParam("inputpasswd") String inputpasswd,
			@RequestParam("files") MultipartFile files,
			HttpServletRequest request, @ModelAttribute("scri") SearchCriteria scri, Model model,
			RedirectAttributes rttr, HttpServletResponse response) throws Exception {

		System.out.println("get modify");
		System.out.println("req = " + request.getAttribute("bno"));
		System.out.println("req = " + request.getParameter("bno"));
		System.out.println("req = " + request.getParameter("passwd"));
		System.out.println("req = " + request.getAttribute("passwd"));
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		//String inputpasswd = request.getParameter("passwd");
		System.out.println("bno = " + bno);
		System.out.println("inputpasswd = " + inputpasswd);
		BoardVO board = service.boardInfo(bno);

		String oldfile = board.getFileOriname();
		System.out.println("service.boardInfo(bno) ok");

		String oldpasswd = board.getPasswd();
		System.out.println(inputpasswd);
		System.out.println("oldpass = " + board.getPasswd());

		System.out.println("bno = " + bno);

		System.out.println("oldfile = " + oldfile);
		System.out.println("files name = " + files.getOriginalFilename());
		System.out.println(files.isEmpty());

		if (oldpasswd.equals(inputpasswd)) {
			System.out.println("passwd ok same");

			if (!files.isEmpty()) {
				System.out.println("input file ok");
				if (board.getFilename() != null || board.getFilename() != "") {
					String fileurl = board.getFileurl();
					String filename = board.getFilename();
					File file = new File(fileurl + filename);

					if (file.exists() || filename != null || filename != "") { 
						if (file.delete()) {
							System.out.println("delete");
						} else {
							System.out.println("no delete");
						}
					} 
				}
			}

			vo.setContent(request.getParameter("content"));
			vo.setTitle(request.getParameter("title"));
			System.out.println("........");
			service.update(vo, files);
		}

		else {
			System.out.println("passwd no same");
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("utf-8");
			writer.println("<script type=text/javascript > alert('passwd fail');  ");
			writer.println("history.back();</script>");
			writer.flush();
		}

		rttr.addAttribute("bno", bno);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		System.out.println("================================");

		return "redirect:/board/read?bno={bno}";

	}

	// 占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윞占쎈��占쎈쐻占쎈윪占쎌졆
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void getDelete(@RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		// logger.info("get delete");
		System.out.println("get delete");
		model.addAttribute("bno", bno);
		model.addAttribute("scri", scri);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String postDelete(@RequestParam("bno") int bno, @RequestParam("passwd") String passwd,
			HttpServletResponse response, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		// logger.info("post delete");

		System.out.println("post delete");
		String oldpasswd = service.boardInfo(bno).getPasswd();
		System.out.println("passwd = " + passwd);
		System.out.println("oldpasswd = " + oldpasswd);

		if (oldpasswd.equals(passwd)) {
			System.out.println("占쎌뵬燁삼옙");
			service.delete(bno);
		} else {
			System.out.println("�겫�뜆�뵬燁삼옙");
			PrintWriter writer = response.getWriter();
			response.setCharacterEncoding("utf-8");
			writer.println("<script type=text/javascript > alert('비밀번호가 틀렸습니다');  ");
			writer.println("history.back();</script>");
			writer.flush();
		}
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/listSearch";
	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		// logger.info("get list page");
		System.out.println("get list page");

		List<BoardVO> list = service.listPage(cri);
		model.addAttribute("list", list);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCount());
		model.addAttribute("pageMaker", pageMaker);

	}

	@RequestMapping(value = "/listSearch", method = RequestMethod.GET)
	public void listSearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		// logger.info("get list search");
		System.out.println("get list search");

		List<BoardVO> list = service.listSearch(scri);
		
		model.addAttribute("list", list);
		

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		
		int totalcount = service.listCount();
		
		pageMaker.setTotalCount(totalcount);
		model.addAttribute("pageMaker", pageMaker);

		System.out.println("page = " + scri.getPage());
		System.out.println("page start = " + scri.getPageStart());
		System.out.println("page getper pagenum = " + scri.getPerPageNum());
		System.out.println(scri.getRowEnd());
		System.out.println(scri.getRowStart());
		
		
	}

	// @RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	// public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes
	// rttr) throws Exception {
	// //logger.info("reply write");
	//
	// RepService.writeReply(vo);
	//
	// rttr.addAttribute("bno", vo.getBno());
	// rttr.addAttribute("page", scri.getPage());
	// rttr.addAttribute("perPageNum", scri.getPerPageNum());
	// rttr.addAttribute("searchType", scri.getSearchType());
	// rttr.addAttribute("keyword", scri.getKeyword());
	//
	// return "redirect:/board/read";
	// }

	// 占쎈쐻占쎈윥占쎈꺔占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윪占쎌젳 POST
	// @RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	// public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes
	// rttr) throws Exception {
	// //logger.info("reply update");
	//
	// RepService.replyUpdate(vo);
	//
	// rttr.addAttribute("bno", vo.getBno());
	// rttr.addAttribute("page", scri.getPage());
	// rttr.addAttribute("perPageNum", scri.getPerPageNum());
	// rttr.addAttribute("searchType", scri.getSearchType());
	// rttr.addAttribute("keyword", scri.getKeyword());
	//
	// return "redirect:/board/read";
	// }

	// 占쎈쐻占쎈윥占쎈꺔占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윞占쎈��占쎈쐻占쎈윪占쎌졆 POST
	// @RequestMapping(value = "/replyDelete", method = RequestMethod.POST)
	// public String replyDelete(ReplyVO vo, SearchCriteria scri, RedirectAttributes
	// rttr) throws Exception {
	// //logger.info("reply delete");
	//
	// RepService.replyDelete(vo);
	//
	// rttr.addAttribute("bno", vo.getBno());
	// rttr.addAttribute("page", scri.getPage());
	// rttr.addAttribute("perPageNum", scri.getPerPageNum());
	// rttr.addAttribute("searchType", scri.getSearchType());
	// rttr.addAttribute("keyword", scri.getKeyword());
	//
	// return "redirect:/board/read";
	// }

	// 占쎈쐻占쎈윥占쎈꺔占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윪占쎌젳 GET
	// @RequestMapping(value = "/replyUpdate", method = RequestMethod.GET)
	// public void getReplyUpdate(@RequestParam("rno") int rno,
	// @ModelAttribute("scri") SearchCriteria scri, Model model)
	// throws Exception {
	// //logger.info("reply update");
	//
	// ReplyVO vo = null;
	//
	// vo = RepService.readReplySelect(rno);
	//
	// model.addAttribute("readReply", vo);
	// model.addAttribute("scri", scri);
	// }

	// // 占쎈쐻占쎈윥占쎈꺔占쎈섀占쎈Ŋ�굲 占쎈쐻占쎈윥占쎈묄占쎈쐻占쎈윪占쎌젳 GET
	// @RequestMapping(value = "/replyDelete", method = RequestMethod.GET)
	// public void getReplyDelete(@RequestParam("rno") int rno,
	// @ModelAttribute("scri") SearchCriteria scri, Model model)
	// throws Exception {
	// //logger.info("reply delete");
	//
	// ReplyVO vo = null;
	//
	// vo = RepService.readReplySelect(rno);
	//
	// model.addAttribute("readReply", vo);
	// model.addAttribute("scri", scri);
	// }

}
