package minho.springserver.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SuccessResponse {
    private final String status = "success";
    private String message;
    private Object data;
}
