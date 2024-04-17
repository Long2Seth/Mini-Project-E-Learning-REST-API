package co.istad.elearning.util;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;


@Accessors(chain = true)
@Data
public class BaseResponse<T> {
    private T payload;
    private String message;
    private Object metadata; // related to pagination
    private  int status;
    public static <T> BaseResponse<T> enrolledSuccess(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("Successfully Enrolled");
    }

    public static <T> BaseResponse<T> found(){
        return new BaseResponse<T>()
                .setStatus(HttpStatus.OK.value())
                .setMessage("Item Found");
    }
}
