package minho.springserver.api.interfaces.board;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import minho.springserver.api.domain.board.BoardInfo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

// DTO -> Create/Read/Modify/Remove로 정의
public class BoardDto {
    // POST-api/posts
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

    // GET-api/posts
    static class ReadPosts {
        @ToString
        @Getter
        static class Data {
            private final Long postId;
            private final String author;
            private final String title;
            private final String content;

            Data(BoardInfo.PostInfo postInfo) {
                this.postId = postInfo.getPostId();
                this.author = postInfo.getAuthor();
                this.title = postInfo.getTitle();
                this.content = postInfo.getContent();
            }
        }
    }

    // GET-api/posts/:postId
    static class ReadPost {
        @ToString
        @Getter // <- getter가 없으면 serialization이 되지 않습니다.
        static class Data {
             private final Long postId;
             private final String author;
             private final String title;
             private final String content;

            Data(BoardInfo.PostInfo postInfo) {
                this.postId = postInfo.getPostId();
                this.author = postInfo.getAuthor();
                this.title = postInfo.getTitle();
                this.content = postInfo.getContent();
            }
        }
    }

    // GET-api/posts/:postId
    static class ModifyPost {
        @Getter
        /* 생성자(new)를 사용하지 않기때문에, @RequiredArgsConstructor가 필요하지 않습니다.
        만일 private final로 member를 선언하면 생성자함수가 필요합니다.
        */
        static class RequestBody {
            // @NotBlank(message = "author is required - '':(X) / ' ':(X) / null:(X)")
            String author;

            // @NotEmpty(message = "title is required - '':(X) / ' ':(O) / null:(X)")
            String title;

            @NotNull(message = "content is required - '':(O) / ' ':(O) / null:(X)")
            String content;
        }

        static class Data {
            private final Long postId;

            Data(Long postId) {
                this.postId = postId;
            }

            // @Getter가 아니여도 getter 함수가 있으면 serialization이 됩니다.
            public Long getPostId() {
                return postId;
            }
        }
    }
}
