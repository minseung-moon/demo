package hello.demo;
// 동일한 패키지의 file들만 spring이 실행되면서 container에 등록을 한다
// component scan의 대상이 되지 않는

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// Tomcat started on port(s): 8080 (http) with context path ''
		// localhost:8080 주소 창에 입력하여 접속!
	}

}
