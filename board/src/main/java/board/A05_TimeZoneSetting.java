package board;

import java.util.TimeZone;

import org.springframework.stereotype.Component;
//  컨테이너에 로딩이 된다.
@Component
public class A05_TimeZoneSetting {
	public A05_TimeZoneSetting() {
		TimeZone tz = TimeZone.getTimeZone("Asia/Seouls");
		TimeZone.setDefault(tz);
	}
}

