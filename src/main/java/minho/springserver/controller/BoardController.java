package minho.springserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import minho.springserver.dto.Post;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RestController
public class BoardController {

    // ObjectMapper?
    private ObjectMapper objectMapper = new ObjectMapper();

    // throws IOException?, inputStream?
    @PostMapping(value = "/api/board/posts")
    public void postPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("info log={}",  messageBody);
        Post post = objectMapper.readValue(messageBody, Post.class);
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

