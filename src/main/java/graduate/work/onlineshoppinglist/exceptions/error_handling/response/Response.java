package graduate.work.onlineshoppinglist.exceptions.error_handling.response;

import graduate.work.onlineshoppinglist.model.responses.DataResponse;
import graduate.work.onlineshoppinglist.model.responses.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private ResponseStatus status;

    private DataResponse<T> data;

    private CustomErrorResponse error;

    public Response(DataResponse<T> data) {
        this.data = data;
        this.status = ResponseStatus.SUCCESS;
        this.error = null;
    }

    public Response(CustomErrorResponse error) {
        this.error = error;
        this.status = ResponseStatus.ERROR;
        this.data = null;
    }

}
