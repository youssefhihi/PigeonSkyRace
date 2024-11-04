package ma.yc.PigeonSkyRace.common.infrastructure.web;

import org.springframework.http.HttpStatus;

public class ApiResponse<ResponseDto> {

    private ResponseDto data;
    private String message;
    private HttpStatus status;

    public ApiResponse(ResponseDto data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public ResponseDto getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }
}