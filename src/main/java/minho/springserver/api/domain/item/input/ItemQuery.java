package minho.springserver.api.domain.item.input;

import lombok.Getter;

// Query Read로 정의
public class ItemQuery {

    @Getter
//    @Builder
    public static class ReadItemQuery {
        private final Long itemId;

        public ReadItemQuery(Long itemId) {
            this.itemId = itemId;
        }
    }
}
