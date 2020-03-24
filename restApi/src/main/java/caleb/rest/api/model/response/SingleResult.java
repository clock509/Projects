//결과를 담을 모델 3개를 정의한다.
//2. 결과가 단일 건인 api를 담는 모델.
package caleb.rest.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResult<T> extends CommonResult{ //<T>: 어떤 형태의 값도 넣을 수 있음. //CommonResult를 상속받으므로, api 요청도 같이 출력된다.
    private T data;
}
