package com.spring.board.persistence;

import java.util.List;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;

public interface BoardDAO {

	public void create(BoardVO board) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void update(BoardVO board) throws Exception;

	public void remove(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public Integer getMaxBno() throws Exception;
	
	List<BoardVO> listPage(Criteria cri) throws Exception;
}