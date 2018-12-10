package com.board.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.board.VO.BoardVO;
import com.board.VO.Criteria;
import com.board.VO.ReplyVO;
import com.board.VO.SearchCriteria;


public interface BoardDAO {

	// 占쎌삂占쎄쉐
	public void write(BoardVO vo, MultipartFile ffiles) throws Exception;

	// 鈺곌퀬�돳
	public BoardVO read(int bno) throws Exception;

	// 占쎈땾占쎌젟
	public void update(BoardVO vo, MultipartFile files) throws Exception;

	// 占쎄텣占쎌젫
	public void delete(int bno) throws Exception;

	// 筌뤴뫖以�
	public List<BoardVO> list() throws Exception;

	// 筌뤴뫖以� + 占쎈읂占쎌뵠筌욑옙
	public List<BoardVO> listPage(Criteria cri) throws Exception;

	// 野껊슣�뻻�눧占� �룯占� 揶쏉옙占쎈땾
	public int listCount() throws Exception;

	// 筌뤴뫖以� + 占쎈읂占쎌뵠筌욑옙 + 野껓옙占쎄퉳
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception;

	// 野껓옙占쎄퉳 野껉퀗�궢 揶쏉옙占쎈땾
	public int countSearch(SearchCriteria scri) throws Exception;

	// 占쎈솊疫뀐옙 鈺곌퀬�돳
	public List<ReplyVO> readReply(int bno) throws Exception;

	public void fileDelete(int bno) throws Exception;

	public BoardVO fileSearch(int bno);

	public String deletePasswd(int bno);

	public void updateViewCnt(int bno);

	public BoardVO boardInfo(int bno);
}
