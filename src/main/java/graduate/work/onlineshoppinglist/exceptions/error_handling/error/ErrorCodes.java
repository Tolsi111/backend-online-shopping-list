package graduate.work.onlineshoppinglist.exceptions.error_handling.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCodes {
    ERROR_CODE1("400-1"),
    ERROR_CODE2("400-2"),
    NOT_FOUND_RECIPE("404-1"),
    NOT_FOUND_SHOPPING_ITEM("404-2"),
    NOT_FOUND_SHOPPING_LIST("404-3"),
    NOT_FOUND_ITEM("404-4"),
    INTERNAL_SERVER_GENERIC_ERROR("500-1"),
    INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE("500-2"),
    BAD_REQUEST_INVALID_PARAMETER_FORMAT("400-3"),
    BAD_REQUEST_MISSING_REQUEST_PARAMETER("400-4"),
    BAD_REQUEST_INVALID_CATEGORY("400-5");
    private final String value;
}
