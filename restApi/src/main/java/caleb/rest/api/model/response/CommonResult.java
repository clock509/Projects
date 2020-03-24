//결과를 담을 모델 3개를 정의한다.
//1. API의 실행 결과를 담는 공통 모델(CommonResult): api의 처리 상태 및 메시지를 내려주는 데이터.

package caleb.rest.api.model.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonResult {

    @ApiModelProperty(value = "응답 성공여부: True/False")
    private boolean success; //success: 성공, 실패 여부를 나타내는 메시지.

    @ApiModelProperty(value = "응답 코드 번호: >= 0 정상, < 0 비정상")
    private int code; //해당 상황에 맞는 응답 코드나 메시지.

    @ApiModelProperty(value = "응답 메시지")
    private String msg;
}
