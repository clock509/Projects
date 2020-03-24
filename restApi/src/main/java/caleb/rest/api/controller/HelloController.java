package caleb.rest.api.controller;

import lombok.Getter;
import lombok.Setter;
//import jdk.nashorn.internal.objects.annotations.Getter;   //lombok용 라이브러리가 아니므로 에러가 난다.
//import jdk.nashorn.internal.objects.annotations.Setter;   //lombok용 라이브러리가 아니므로 에러가 난다.
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

//Controller는 클라이언트 요청의 진입점이 되는 class임.
@Controller //Spring에 해당 Class가 Controller임을 알려주기 위해 class명 상단에 @Controller를 붙여준다.
public class HelloController {
    /*
    1. 화면에 helloworld가 출력됩니다.
    */
    @GetMapping(value = "/helloworld/string") //@GetMapping(“RequestURI”): 해당 주소의 리소스를 이용하기 위해 Get method로 호출해야 한다는 의미.
                                              //웹 브라우저에서 http://localhost:8080/helloworld/string 을 실행하면 Get 방식으로 호출되고, /helloworld/string을 mapping하고 있는 메서드가 실행됨.
    @ResponseBody //@ResponseBody: 결과를 응답에 그대로 출력한다는 의미. @ResponseBody를 지정하지 않으면, return에 지정된 "helloworld" 이름으로 된 파일을 프로젝트 경로에서 찾아 화면에 출력한다.
    public String helloworldString() {
        return "helloworld";
    }
    /*
    2. 화면에 {message:"helloworld"} 라고 출력됩니다.
    */
    @GetMapping(value = "/helloworld/json")
    @ResponseBody
    public Hello helloworldJson() {
        Hello hello = new Hello();
        hello.message = "helloworld";
        return hello;
    }
    /*
    3. 화면에 helloworld.ftl의 내용이 출력됩니다.
    */
    @GetMapping(value = "/helloworld/page") //ResponseBody를 지정하지 않으면 Spring에서는 해당 결과를 표시해주기 위한 페이지를 찾는데 해당 페이지가 없으므로 404에러가 발생한다.
    public String helloworld() {            //resources/templates/helloworld.tfl 파일 생성.
        return "helloworld";
    }

    @Setter
    @Getter
    public static class Hello {
        private String message;
    }
    //////////////////////////////////////////////////////////
    @GetMapping(value = "/helloworld/helm")
    @ResponseBody
//    public String hellohelm() throws IOException {
    public ArrayList shellCmd(String command) throws Exception {
//        Process p = Runtime.getRuntime().exec("kubectl get pods -o wide");
//        BufferedReader br = new BufferedReader((new InputStreamReader(p.getInputStream())));
//        String line = null;
//
//        for (int i = 0; i < 4 ; i++) {
//            System.out.println(br.readLine());
//        }
//        return br.readLine();
//    }
        ArrayList result = new ArrayList();
        try {
            Process p = Runtime.getRuntime().exec("kubectl get pods -o wide");
            BufferedReader br = new BufferedReader((new InputStreamReader(p.getInputStream())));
            String line = null;


            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result.add(line);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
//띄어쓰기가 두 개 이상이면(=\r\r?>) 한 컬럼이 끝난 것으로 인식.