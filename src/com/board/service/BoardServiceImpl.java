package com.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.board.VO.BoardVO;
import com.board.VO.Criteria;
import com.board.VO.ReplyVO;
import com.board.VO.SearchCriteria;
import com.board.dao.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	// �뜝�럩�굚�뜝�럡�뎽
	@Override
	public void write(BoardVO vo, MultipartFile files) throws Exception {
		dao.write(vo,files);
	}

	// �댖怨뚰�э옙�뤂
	
	@Transactional
	@Override
	public BoardVO read(int bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	// �뜝�럥�빢�뜝�럩�젧
	@Override
	public void update(BoardVO vo, MultipartFile files) throws Exception {
		dao.update(vo,files);
	}

	// �뜝�럡�뀭�뜝�럩�젷
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	// 嶺뚮ㅄ維뽨빳占�
	@Override
	public List<BoardVO> list() throws Exception {
		System.out.println("Service list");
		return dao.list();
	}

	// 嶺뚮ㅄ維뽨빳占� + �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		System.out.println("Service list page");
		return dao.listPage(cri);
	}

	// �뇦猿딆뒩占쎈뻣占쎈닱�뜝占� 占쎈／�뜝占� �뤆�룊�삕�뜝�럥�빢
	@Override
	public int listCount() throws Exception {
		return dao.listCount();
	}

	// 嶺뚮ㅄ維뽨빳占� + �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 + �뇦猿볦삕�뜝�럡�돰
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		System.out.println("Service list search");
		return dao.listSearch(scri);
	}

	// �뇦猿볦삕�뜝�럡�돰 �뇦猿됲�쀯옙沅� �뤆�룊�삕�뜝�럥�빢
	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		return dao.countSearch(scri);
	}

	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}

	

	public void fileDelete(int bno) throws Exception {
		dao.fileDelete(bno);
	}

	public String deletePasswd(int bno) {
		return dao.deletePasswd(bno);
	}

	@Override
	public BoardVO fileSearch(int bno) {
		return dao.fileSearch(bno);
	}
	public BoardVO boardInfo(int bno) {
		return dao.boardInfo(bno);
	}

}