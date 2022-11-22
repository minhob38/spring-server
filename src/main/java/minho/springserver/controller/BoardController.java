package minho.springserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import minho.springserver.dao.PostsRepository;
import minho.springserver.dto.Post;
import minho.springserver.dto.SuccessResponse;
import minho.springserver.entity.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Transactional
@RequestMapping(value = "/api/board")
@RestController
public class BoardController {
    private final PostsRepository postsRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BoardController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @PostMapping(value = "/posts")
    public void postPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        Post post = objectMapper.readValue(messageBody, Post.class);
        String author = post.getAuthor();
        String title = post.getTitle();
        String content = post.getContent();
        this.postsRepository.save(author, title, content);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("created post");
        String successResponseAsJson = objectMapper.writeValueAsString(successResponse);
        response.getWriter().write(successResponseAsJson);
    }

    @GetMapping(value = "/posts")
    public ResponseEntity<SuccessResponse> getPosts() {
        List<Posts> posts = this.postsRepository.findAll();
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("found posts");
        successResponse.setData(posts);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    // TODO: bean validation 처리
    @GetMapping(value = "/posts/{postId}")
    public ResponseEntity<SuccessResponse> getPost(@PathVariable("postId") Long postId) {
        System.out.println(postId.getClass().getName()); // Long으로 자동 형변환 되는듯 합니다. : )
        Posts post = this.postsRepository.findById(postId);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("found post");
        successResponse.setData(post);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    /* https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestbody */
    @PatchMapping(value = "/posts")
    public String patchPost(@RequestBody Post post) { //@Requestbody를 생략하면, @ModelAttribute가 붙음
        log.info("author={}, title={}, content={}", post.getAuthor(), post.getTitle(), post.getContent());
        return "ok";
    }
//    @PostMapping(value = "/api/board/posts")
//    public void postPost(InputStream inputStream, Writer reponseWriter) throws IOException {
//        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
//        log.info("info log={}",  messageBody);
//        reponseWriter.write("ok");
//    }

//    @PostMapping(value = "/api/board/posts")
//    public HttpEntity<String> postPost(HttpEntity<String> httpEntity) throws IOException {
//        String messageBody = httpEntity.getBody();
//        log.info("info log={}",  messageBody);
//       return new HttpEntity<>("ok"); // Response entity, request entity
//    }

    // @ResponseBody
    //    @PostMapping(value = "/api/board/posts")
//    public HttpEntity<String> postPost(@RequestBody String messageBody) throws IOException {
//        String messageBody = httpEntity.getBody();
//        log.info("info log={}",  messageBody);
//       return new HttpEntity<>("ok"); // Response entity, request entity
//    }
}

