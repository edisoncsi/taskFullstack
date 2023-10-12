package eco.com.spring.mcsv.tasks.responses;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-tasks
 */

public class BaseResponse {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
