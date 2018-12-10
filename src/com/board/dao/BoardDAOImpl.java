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

	// 嶺뚮씭�쐠占쎈턄�뛾�룆占쏙옙堉믣뜝�럥裕�
	@Inject
	private SqlSession sql;

	// 嶺뚮씞�걢占쎌뱺
	private static String namespace = "com.board.mappers.boardMapper";

	// �뜝�럩�굚�뜝�럡�뎽
	@Override
	public void write(BoardVO vo, MultipartFile files) throws Exception {

		sql.insert(namespace + ".write", fileWrite(vo, files));

	}

	// �댖怨뚰�э옙�뤂
	@Override
	public BoardVO read(int bno) throws Exception {
		return sql.selectOne(namespace + ".read", bno);
	}

	// �뜝�럥�빢�뜝�럩�젧
	@Override
	public void update(BoardVO vo, MultipartFile files) throws Exception {
		BoardVO board = fileWrite(vo, files);

		if (board.getFilename() == null || board.getFilename() == "") {
			System.out.println("�뙆�씪 �뾾�뒗 update");
			sql.update(namespace + ".update", board);
		} else
			sql.update(namespace + ".fileupdate", board);
	}

	// �뜝�럡�뀭�뜝�럩�젷
	@Override
	public void delete(int bno) throws Exception {
		sql.delete(namespace + ".delete", bno);
	}

	// 嶺뚮ㅄ維뽨빳占�
	@Override
	public List<BoardVO> list() throws Exception {
		return sql.selectList(namespace + ".list");
	}

	// 嶺뚮ㅄ維뽨빳占� + �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕
	@Override
	public List<BoardVO> listPage(Criteria cri) throws Exception {
		return sql.selectList(namespace + ".listPage", cri);
	}

	// �뇦猿딆뒩占쎈뻣占쎈닱�뜝占� 占쎈／�뜝占� �뤆�룊�삕�뜝�럥�빢
	@Override
	public int listCount() throws Exception {
		return sql.selectOne(namespace + ".listCount");
	}

	// 嶺뚮ㅄ維뽨빳占� + �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕 + �뇦猿볦삕�뜝�럡�돰
	@Override
	public List<BoardVO> listSearch(SearchCriteria scri) throws Exception {
		System.out.println("search keyword = " + scri.getKeyword());

		return sql.selectList(namespace + ".listSearch", scri);
	}

	// �뇦猿볦삕�뜝�럡�돰 �뇦猿됲�쀯옙沅� �뤆�룊�삕�뜝�럥�빢
	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		return sql.selectOne(namespace + ".countSearch", scri);
	}

	// �뜝�럥�냺�뼨�먯삕 �댖怨뚰�э옙�뤂
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
			System.out.println("�삱由� �뙆�씪�씠 �뾾�뼱");
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