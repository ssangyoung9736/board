package board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import board.vo.Board;
import board.vo.BoardFile;
import board.vo.BoardSch;

@Service
public class A02_Service {
	@Autowired(required=false)
	private A03_Dao dao;
	public List<Board> boardList(BoardSch sch){
		if(sch.getSubject()==null) sch.setSubject("");
		if(sch.getWriter()==null) sch.setWriter("");
		return dao.boardList(sch);
	}

	public void uptReadCnt(int no) {
		dao.uptReadCnt(no);
	}	
	@Value("${user.upload}")
	private String upload;

	private void uploadFile(MultipartFile f){
		String fname = f.getOriginalFilename();
		File fObj = new File(upload+fname);
		try{
			System.out.println("## 파일 업로드 처리:"+upload+fname);
			f.transferTo(fObj);
		}catch(Exception e){
				System.out.println("업로드예외:"+e.getMessage());
		}			
	}
	public void insertBoard(Board ins) {
		dao.insertBoard(ins);
		String fname = ins.getReport().getOriginalFilename();
		if( !fname.equals("") ){
			uploadFile(ins.getReport());
			BoardFile f = new BoardFile();
			f.setFname(fname);
			f.setEtc(ins.getSubject());
			dao.insertUploadFile(f);
		}
		
	}		
	public Board getBoard(int no) {
		Board b = dao.getBoard(no);
		// 해당 번호에 파일 정보를 가져오는 처리..
		b.setFname(dao.getBoardFile(no));
		return b;
	}	
	
}
