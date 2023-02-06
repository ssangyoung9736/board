package board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import board.vo.Board;
import board.vo.BoardSch;

@Controller
public class A01_Controller {
	@Autowired(required=false)
	private A02_Service service;
	// http://localhost:7080/board/list.do
	@RequestMapping("/list.do")
	public String boardList(
			@ModelAttribute("sch") BoardSch sch,Model d) {
		d.addAttribute("list", service.boardList(sch));
		return "a01_boardList";
	}
	@GetMapping("/insertFrm.do")
	public String inserFrm(){
		return "a02_insertBoard";
	}
	@PostMapping("/insert.do")
	public String insert(Board ins, Model d){
		service.insertBoard(ins);
		d.addAttribute("msg","등록성공");
		return "a02_insertBoard";
	}	
}
