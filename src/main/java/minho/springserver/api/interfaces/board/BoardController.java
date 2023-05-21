package minho.springserver.api.interfaces.board;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minho.springserver.api.application.board.BoardApplication;
import minho.springserver.api.domain.board.BoardCommand;
import minho.springserver.api.domain.board.BoardInfo;
import minho.springserver.api.domain.board.BoardQuery;
import minho.springserver.dto.SuccessResponse;
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
import javax.validation.constraints.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

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
        System.out.println(violations.size());
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
        Long insertedId = this.boardApplication.createPost(command);

        // 응답 만들기
        BoardDto.CreatePost.Data data = new BoardDto.CreatePost.Data(insertedId);
        System.out.println(data);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("created post");
        successResponse.setData(data);
        System.out.println(successResponse);
        String successResponseAsJson = objectMapper.writeValueAsString(successResponse); // object -> json

        // 응답 보내기
        response.setContentType("application/json");
        response.getWriter().write(successResponseAsJson);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<SuccessResponse> getPosts() {
        // interface -> application
        List<BoardInfo.PostInfo> posts = this.boardApplication.findPosts();

        // 응답 만들기
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("found posts");
        successResponse.setData(posts);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    // TODO: bean validation 처리
    @GetMapping(value = "/posts/{postId}")
    public SuccessResponse getPost(@PathVariable("postId") Long postId) {
        System.out.println(postId.getClass().getName()); // Long으로 자동 형변환 되는듯 합니다. : )

        // query 만들기
        BoardQuery.FindPostQuery query = new BoardQuery.FindPostQuery(postId);

        // interface -> application
        BoardInfo.PostInfo post = this.boardApplication.findPost(query);
        System.out.println(post);

        // dto 만들기: TODO: mapper
        BoardDto.ReadPost.Data data = new BoardDto.ReadPost.Data(post); // dto 만들기
        System.out.println("!!!");
        System.out.println(data);

        //  응답 만들기
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("found post");
//        successResponse.setData(data);
        return successResponse;
    }
//
//    /* https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestbody */
//    @PatchMapping(value = "/posts/{postId}")
//    public ResponseEntity<SuccessResponse> patchPost(
//            @Validated @RequestBody Post update, // RequestBody 및 ModelAttribute는 유효성검증을 위해 @Validated가 붙어있어야 합니다.
//            @Min(1) @PathVariable("postId") Long postId
//    ) throws BoardException { //@Requestbody를 생략하면, @ModelAttribute가 붙습니다.
//        Posts post = this.postsRepository.findById(postId);
//        if (post == null) throw new BoardException("post does not exits :(");
//
//        this.postsRepository.update(postId, update);
//        SuccessResponse successResponse = new SuccessResponse();
//        successResponse.setMessage("edited post");
//        return new ResponseEntity<>(successResponse, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/posts/{postId}")
//    public ResponseEntity<SuccessResponse> deletePost(@PathVariable("postId") Long postId) {
//        this.postsRepository.delete(postId);
//        SuccessResponse successResponse = new SuccessResponse();
//        successResponse.setMessage("deleted post");
//        return new ResponseEntity<>(successResponse, HttpStatus.OK);
//    }
}

