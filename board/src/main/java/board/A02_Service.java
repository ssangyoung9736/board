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
		// 1. 총페이지 수
		sch.setCount(dao.totCnt(sch));
		// 2. 현재페이지 번호(클릭한)
		if(sch.getCurPage()==0) {
			sch.setCurPage(1);
		}
		// 3. 한페이지에 보일 데이터 갯수
		//   - 초기화면 현재 페이지 번호 0 ==> default설정
		if(sch.getPageSize()==0) {
			sch.setPageSize(5);
		}
		// 4. 총페이지 수.(전체데이터/한페이지에 보일 데이터 건수)
		//    한번에 보일 데이터 건수 5건일 때, 총건수11 ==> 3페이지
		sch.setPageCount(
				(int)Math.ceil(
				sch.getCount()/(double)sch.getPageSize())
				);
		// 5. 마지막 번호
		sch.setEnd(sch.getCurPage()*sch.getPageSize());
		sch.setStart((sch.getCurPage()-1)*sch.getPageSize()+1);
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
	public void updateBoard(Board upt) {
		dao.updateBoard(upt);
	}
	public void deleteBoard(int no){
		dao.deleteBoard(no);
	}
	
}
