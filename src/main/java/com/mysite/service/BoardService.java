package com.mysite.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysite.repository.BoardDao;
import com.mysite.vo.BoardVo;
import com.mysite.vo.PageVo;

@Service
public class BoardService {
	@Autowired
	BoardDao boardDao;
	@Autowired
	PageVo pageVo;
	
	public List<BoardVo> getList(int currNo, int pageNo) {
		int totalCount = boardDao.getTotalCount();
		return boardDao.getList(currNo, pageNo, totalCount);
	}
	
	public PageVo getPage(int currNo) {
		int totalCount = boardDao.getTotalCount();
		pageVo.PageSetting(currNo, totalCount);
		return pageVo;
	}
	
	public int insert(BoardVo boardVo) {
		String content = boardVo.getContent().replace("\r\n", "<br/>");
		boardVo.setContent(content);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = sdf.format(cal.getTime());
		
		boardVo.setRegDate(date);
		
		return boardDao.insert(boardVo);
	}
	
	public BoardVo read(int no) {
		return boardDao.read(no);
	}
}