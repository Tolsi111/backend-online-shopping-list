package graduate.work.onlineshoppinglist.model.responses;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataResponse<T> {

    private T item;
    private List<T> items;

    public DataResponse(T item) {
        this.item = item;
        this.items = null;
    }

    public DataResponse(List<T> items) {
        this.item = null;
        this.items = items;
    }
}
