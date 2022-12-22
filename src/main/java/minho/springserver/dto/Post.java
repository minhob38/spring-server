package minho.springserver.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class Post {
    @NotBlank(message = "author is required - '':(X) / ' ':(X) / null:(X)")
    String author;

    @NotEmpty(message = "title is required - '':(X) / ' ':(O) / null:(X)")
    String title;

    @NotNull(message = "content is required - '':(O) / ' ':(O) / null:(X)")
    String content;
}
