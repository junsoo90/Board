package com.board.dao;
import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.board.VO.BoardVO;
import com.board.VO.Criteria;
import com.board.VO.ReplyVO;
import com.board.VO.SearchCriteria;


@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;

	private static String namespace = "com.board.mappers.boardMapper";

	@Override
	public void write(BoardVO vo, MultipartFile files) throws Exception {

		sql.insert(namespace + ".write", fileWrite(vo, files));

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return sql.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo, MultipartFile files) throws Exception {
		BoardVO board = fileWrite(vo, files);

		if (board.getFilename() == null || board.getFilename() == "") {
			System.out.println("占쎈솁占쎌뵬 占쎈씨占쎈뮉 update");
			sql.update(namespace + ".update", board);
		} else
			sql.update(namespace + ".fileupdate", board);
	}

	@Override
	public void delete(int bno) throws Exception {
		sql.delete(namespace + ".delete", bno);
	}

	@Override
	public List<BoardVO> list() throws Exception {
		System.out.println("list in");
		return sql.selectList(namespace + ".list");
	}

	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		System.out.println("list page in");
		return sql.selectList(namespace + ".listPage", cri);
	}

	@Override
	public int listCount() throws Exception {
		System.out.println("list count in");
		return sql.selectOne(namespace + ".listCount");
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		System.out.println("list search in");
		System.out.println("search keyword = " + scri.getKeyword());
		
		return sql.selectList(namespace + ".listSearch", scri);
	}

	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		return sql.selectOne(namespace + ".countSearch", scri);
	}

	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return sql.selectList(namespace + ".readRpley", bno);
	}

	public void fileDelete(int bno) throws Exception {
		sql.update(namespace + ".fileDelete", bno);
	}

	public String deletePasswd(int bno) {
		return sql.selectOne(namespace + ".deletepasswd", bno);
	}

	public void updateViewCnt(int bno) {
		sql.update(namespace + ".viewcnt", bno);
	}

	public BoardVO boardInfo(int bno) {
		return sql.selectOne(namespace + ".boardinfo", bno);
	}

	public BoardVO fileSearch(int bno) {
		return sql.selectOne(namespace + "/fileSearch");
	}

	public BoardVO fileWrite(BoardVO board, MultipartFile files) throws Exception {

		if (files.isEmpty()) {
			System.out.println("占쎌궞�뵳占� 占쎈솁占쎌뵬占쎌뵠 占쎈씨占쎈선");
			return board;
		}
		System.out.println("files name = " + files.getOriginalFilename());
		System.out.println(files.isEmpty());

		String orifilename = files.getOriginalFilename();

		String extension = orifilename.substring(orifilename.indexOf(".")); // .jpg
		System.out.println(extension);

		String fileurl = "C:\\Users\\junso\\Desktop\\folder\\";
		String savefilename = dateForm() + "_" + orifilename;

		File file = new File(fileurl);
		System.out.println("exist = " + file.exists());
		System.out.println("fileurl = " + fileurl);

		if (!file.exists()) {
			System.out.println("!file exist");
			file.mkdir();
		}

		if (!files.isEmpty()) {
			System.out.println("!files.isEmpty");
			file = new File(fileurl + savefilename);
			files.transferTo(file);
		}

		System.out.println("complete");

		board.setFileOriname(orifilename);
		board.setFileurl(fileurl);
		board.setFilename(savefilename);
		System.out.println("savefilename = " + savefilename);
		System.out.println("filename = " + orifilename);

		return board;
	}

	public String savafileForm(MultipartFile files) {
		String orifilename = files.getOriginalFilename();
		String extension = orifilename.substring(orifilename.indexOf(".")); // .jpg
		return dateForm() + "_" + orifilename;
	}

	public String dateForm() {
		long time = System.currentTimeMillis();
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd-HH-mm-ss", Locale.KOREA);
		return dayTime.format(new Date(time));
	}
}