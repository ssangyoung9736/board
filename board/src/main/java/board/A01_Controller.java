package board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class A01_Controller {

	@RequestMapping("/list.do")
	public String boardList() {
		return "a01_boardList";
	}
}
