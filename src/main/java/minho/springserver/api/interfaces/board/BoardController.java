package minho.springserver.api.interfaces.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minho.springserver.api.application.board.BoardApplication;
import minho.springserver.api.domain.board.BoardCommand;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.api.domain.board.BoardQuery;
import minho.springserver.exception.BoardException;
import minho.springserver.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Validated // @RequestParam, @PathVariable 유효성검증을 위해 붙여야 합니다.
@Transactional
@RequiredArgsConstructor // private final variable 기반의 constructor를 만듭니다.
@RequestMapping(value = "/api/board")
@RestController
public class BoardController {
    private final BoardApplication boardApplication;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    @PostMapping(value = "/posts")
    public void postPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // request body 만들기
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        BoardDto.CreatePost.RequestBody requestBody = objectMapper.readValue(messageBody, BoardDto.CreatePost.RequestBody.class); // json -> object

        // request body 유효성 check
        Set<ConstraintViolation<BoardDto.CreatePost.RequestBody>> violations = validator.validate(requestBody);

        for (ConstraintViolation<BoardDto.CreatePost.RequestBody> violation : violations) {
            System.out.println("violation=" + violation);
            System.out.println("violation.message=" + violation.getMessage());
            throw new Error(violation.getMessage()); // TODO: BadRequestException 만들기
        }

        // command 만들기
        String author = requestBody.getAuthor();
        String title = requestBody.getTitle();
        String content = requestBody.getContent();
        BoardCommand.CreatePostCommand command = new BoardCommand.CreatePostCommand(author, title, content);

        // interface -> application
        Long createdId = this.boardApplication.createPost(command);

        // dto 만들기
        BoardDto.CreatePost.Data data = new BoardDto.CreatePost.Data(createdId);

        // 응답 만들기
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("created post");
        successResponse.setData(data);
        String successResponseAsJson = objectMapper.writeValueAsString(successResponse); // object -> json

        // 응답 보내기
        response.setContentType("application/json");
        response.getWriter().write(successResponseAsJson);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<SuccessResponse> getPosts() {
        // interface -> application
        List<BoardInfo.PostInfo> posts = this.boardApplication.readPosts();

        // dto 만들기
        List<BoardDto.ReadPosts.Data> data = posts.stream().map(post -> new BoardDto.ReadPosts.Data(post)).collect(Collectors.toList());

        // 응답 만들기
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("found posts");
        successResponse.setData(data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    // TODO: bean validation 처리
    @GetMapping(value = "/posts/{postId}")
    public ResponseEntity<SuccessResponse> getPost(@PathVariable("postId") Long postId) throws BoardException {
        System.out.println(postId.getClass().getName()); // Long으로 자동 형변환 되는듯 합니다. : )

        // query 만들기
        BoardQuery.ReadPostQuery query = new BoardQuery.ReadPostQuery(postId);

        // interface -> application
        BoardInfo.PostInfo post = this.boardApplication.readPost(query);

        // dto 만들기
        BoardDto.ReadPost.Data data = new BoardDto.ReadPost.Data(post);

        // 응답 만들기
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("found post");
        successResponse.setData(data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    // https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestbody
    @PatchMapping(value = "/posts/{postId}")
    public ResponseEntity<SuccessResponse> patchPost(
            @Validated @RequestBody BoardDto.ModifyPost.RequestBody requestBody, // RequestBody 및 ModelAttribute는 유효성검증을 위해 @Validated가 붙어있어야 합니다.
            @Min(1) @PathVariable("postId") Long postId
    ) throws BoardException { //@Requestbody를 생략하면, @ModelAttribute가 붙습니다.
        // command 만들기
        String author = requestBody.getAuthor();
        String title = requestBody.getTitle();
        String content = requestBody.getContent();
        BoardCommand.ModifyCommand command = new BoardCommand.ModifyCommand(postId, author, title, content);

        System.out.println(command);
        // interface -> application
        Long modifiedId = this.boardApplication.modifyPost(command);

        // dto 만들기
        BoardDto.ModifyPost.Data data = new BoardDto.ModifyPost.Data(modifiedId);

        // 응답 만들기
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("edited post");
        successResponse.setData(data);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }
//
//    @DeleteMapping(value = "/posts/{postId}")
//    public ResponseEntity<SuccessResponse> deletePost(@PathVariable("postId") Long postId) {
//        this.postsRepository.delete(postId);
//        SuccessResponse successResponse = new SuccessResponse();
//        successResponse.setMessage("deleted post");
//        return new ResponseEntity<>(successResponse, HttpStatus.OK);
//    }
}

