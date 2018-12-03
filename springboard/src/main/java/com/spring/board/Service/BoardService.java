package com.spring.board.Service;

import java.util.List;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;

public interface BoardService {
	public void regist(BoardVO board) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void modify(BoardVO board) throws Exception;
	public void remove(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	List<BoardVO> listPage(Criteria cri) throws Exception;
}