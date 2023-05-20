package minho.springserver.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class SuccessResponse {
    private final String status = "success";
    private String message;
    private Object data;
}
