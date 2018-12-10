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

	// 占쎌삂占쎄쉐
	@Override
	public void write(BoardVO vo, MultipartFile files) throws Exception {
		dao.write(vo,files);
	}

	// 鈺곌퀬�돳
	
	@Transactional
	@Override
	public BoardVO read(int bno) throws Exception {
		dao.updateViewCnt(bno);
		return dao.read(bno);
	}

	// 占쎈땾占쎌젟
	@Override
	public void update(BoardVO vo, MultipartFile files) throws Exception {
		dao.update(vo,files);
	}

	// 占쎄텣占쎌젫
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	// 筌뤴뫖以�
	@Override
	public List<BoardVO> list() throws Exception {
		return dao.list();
	}

	// 筌뤴뫖以� + 占쎈읂占쎌뵠筌욑옙
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		return dao.listPage(cri);
	}

	// 野껊슣�뻻�눧占� �룯占� 揶쏉옙占쎈땾
	@Override
	public int listCount() throws Exception {
		return dao.listCount();
	}

	// 筌뤴뫖以� + 占쎈읂占쎌뵠筌욑옙 + 野껓옙占쎄퉳
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		return dao.listSearch(scri);
	}

	// 野껓옙占쎄퉳 野껉퀗�궢 揶쏉옙占쎈땾
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