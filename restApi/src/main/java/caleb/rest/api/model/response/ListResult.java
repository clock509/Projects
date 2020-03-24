//결과를 담을 모델 3개를 정의한다.
//3. 결과가 여러 건인 api를 담는 모델.
package caleb.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ListResult<T> extends CommonResult { //결과 필드를 List 형태로 정의하고, Generic interface에 <T>를 지정하여 어떤 형태의 List값도 넣을 수 있도록 구현
    private List<T> list; //CommonResult를 상속받으므로 api 요청 결과도 같이 출력됨.
}
