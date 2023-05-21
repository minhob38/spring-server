package minho.springserver.response;

import lombok.*;


@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private Status status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<T>(Status.SUCCESS, data, message);
    }

    public static <T> ApiResponse<T> success(T data) {
        return success(data, null);
    }

    // public 떼보기

    enum Status {
        SUCCESS, FAILURE
    }
}

