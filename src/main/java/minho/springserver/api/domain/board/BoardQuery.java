package minho.springserver.api.domain.board;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

public class BoardQuery {

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static class FindPostQuery {
        private final Long postId;
    }
}
