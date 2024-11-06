package ma.yc.PigeonSkyRace.common.infrastructure.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final T data;
    private final String message;
    private final int status;

    public ApiResponse(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status.value();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, HttpStatus.OK);
    }

    public static <T> ApiResponse<T> created(T data, String message) {
        return new ApiResponse<>(data, message, HttpStatus.CREATED);
    }
}
