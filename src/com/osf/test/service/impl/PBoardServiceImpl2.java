package com.osf.test.service.impl;

import java.util.List;
import java.util.Map;

import com.osf.test.dao.PBoardDAO2;
import com.osf.test.dao.impl.PBoardDAOImpl2;
import com.osf.test.service.PBoardService2;
import com.osf.test.vo.PhotoBoardVO;

public class PBoardServiceImpl2 implements PBoardService2 {
	PBoardDAO2 pbdao = new PBoardDAOImpl2();
	@Override
	public int insertPBoard(PhotoBoardVO pBoard) {
		
		return pbdao.insertPBoard(pBoard);
	}

	@Override
	public List<PhotoBoardVO> selectPBoardList() {
		
		return pbdao.selectPBoardList();
	}

	@Override
	public PhotoBoardVO selectPBoard(int pbNum) {
		
		return pbdao.selectPBoard(pbNum);
	}

}
