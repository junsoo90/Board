package com.board.VO;

public class FileVO {
	private int bno;
	private String fileName;
	private String fileUrl;
	private String fileoriname;
	public String getFileoriname() {
		return fileoriname;
	}

	public void setFileoriname(String fileoriname) {
		this.fileoriname = fileoriname;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

}
