package caleb.rest.api.controller.v1;

import caleb.rest.api.entity.User;
import caleb.rest.api.model.response.CommonResult;
import caleb.rest.api.model.response.ListResult;
import caleb.rest.api.model.response.SingleResult;
import caleb.rest.api.repo.UserJpaRepo;
import caleb.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
//  H2 Database 연동을 위한 부분
@RequiredArgsConstructor //class 상단에 선언하면 class 내부에 final로 선언된 객체에 대해서 Constructor Injection을 수행한다. 해당 어노테이션을 사용하지 않고 선언된 객체에 @Autowired를 사용해도 된다.
@RestController // 결과값을 JSON으로 출력한다.
@RequestMapping(value = "/v1") //api resource를 버젼별로 관리하기 위해 /v1을 모든 리소스 주소에 적용되도록 처리한다.
public class UserController {
    private final UserJpaRepo userJpaRepo;

    @GetMapping(value = "/user") //user테이블에 있는 데이터를 모두 읽어온다. 데이터가 한 개 이상일 수 있으므로 리턴 타입은 List<User>로 선언한다.
    public List<User> findAllUser() {
        return userJpaRepo.findAll(); //JPA를 사용하면 기본으로 CRUD에 대해서는 별 다른 설정 없이 쿼리를 질의할 수 있도록 메소드를 지원한다. findAll()은 select msrl, name, uid from user; 쿼리를 내부적으로 실행시켜 준다.
    }

    @PostMapping(value = "/user") //user테이블에 데이터를 1건 입력한다.
    public User save() {
        User user = User.builder()
                .uid("8kwchoi8@naver.com")
                .name("Choi")
                .build();
        return userJpaRepo.save(user); //userJpaRepo.save(user);도 역시 JPA에서 기본으로 제공하는 메소드이다.
                                       //user 객체를 전달하면 다음과 같이 내부적으로 insert문을 실행시켜 줍니다: insert into user(msrl, name, uid) values(null, ?, ?)
    }
}

//웹 브라우저를 열고 http://localhost:8080/v1/user를 실행한다. 최초에는 데이터가 없으므로 []만 리턴됨.

//데이터 입력을 위해 POST로 http://localhost:8080/v1/user를 실행합니다.

//웹브라우저 입력으로는 GET URL만 호출할 수 있으므로 테스트를 위해 아래 링크의 POSTMAN을 다운받아 설치합니다.
//https://www.getpostman.com/downloads/
*/

@Api(tags = {"1. User"}) //UserController를 대표하는 최상단 타이틀 영역에 표시될 값 세팅.
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1")
public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userJpaRepo.findAll());
    }

    @ApiOperation(value = "회원 단건 조회", notes = "userId로 회원을 조회한다")
    @GetMapping(value = "/user/{msrl}")
    public SingleResult<User> findUserById(@ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findById(msrl).orElse(null));
    }

    @ApiOperation(value = "회원 입력", notes = "회원을 입력한다")
    @PostMapping(value = "/user")
    public SingleResult<User> save(@ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
                                   @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
            @ApiParam(value = "회원아이디", required = true) @RequestParam String uid,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .msrl(msrl)
                .uid(uid)
                .name(name)
                .build();
        return responseService.getSingleResult(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "userId로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable long msrl) {
        userJpaRepo.deleteById(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}