package minho.springserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import minho.springserver.dao.PostsRepository;
import minho.springserver.dto.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Transactional
@RequestMapping(value = "/api/board")
@RestController
public class BoardController {
    private final PostsRepository postsRepository;

    @Autowired
    public BoardController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(value = "/posts")
    public void postPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("info log={}",  messageBody);
        Post post = objectMapper.readValue(messageBody, Post.class);
        String author = post.getAuthor();
        String title = post.getTitle();
        String content = post.getContent();
        this.postsRepository.savePost(author, title, content);
        response.getWriter().write("ok");
        log.info("author={}, title={}, content={}", post.getAuthor(), post.getTitle(), post.getContent());
    }

    /* https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestbody */
    @PatchMapping(value = "/api/board/posts")
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

