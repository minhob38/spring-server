package minho.springserver.api.interfaces.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import minho.springserver.api.domain.board.BoardInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BoardDto {
    static class CreatePost {
         @Getter
         @ToString
         static class RequestBody {
             @NotBlank(message = "author is required - '':(X) / ' ':(X) / null:(X)")
             String author;

             @NotEmpty(message = "title is required - '':(X) / ' ':(O) / null:(X)")
             String title;

             @NotNull(message = "content is required - '':(O) / ' ':(O) / null:(X)")
             String content;

             @JsonCreator // jackson에서 json -> inner static class로 바꾸기 위해 필요한 설정입니다.
             RequestBody(@JsonProperty("author") String author, @JsonProperty("title") String title, @JsonProperty("content") String content) {
                 this.author = author;
                 this.title = title;
                 this.content = content;
             }
         }

        @ToString
        @RequiredArgsConstructor
        static class Data {
            @JsonProperty("postId") // jackson에서 nested object -> json으로 바꾸기 위해 필요한 설정입니다.
            private final Long postId;
        }
    }

    // GET-api/posts/:postId

    @ToString
    @Getter // <- getter가 없으면 serialization이 되지 않습니다.
    static class ReadPostData {
        private final Long postId;
        private final String author;
        private final String title;
        private final String content;

        ReadPostData(BoardInfo.PostInfo postInfo) {
            this.postId = postInfo.getPostId();
            this.author = postInfo.getAuthor();
            this.title = postInfo.getTitle();
            this.content = postInfo.getContent();
        }
    }
}
