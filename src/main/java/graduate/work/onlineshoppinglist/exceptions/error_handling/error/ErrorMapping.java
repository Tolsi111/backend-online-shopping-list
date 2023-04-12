package graduate.work.onlineshoppinglist.exceptions.error_handling.error;

import java.util.EnumMap;

public class ErrorMapping {
    private static final EnumMap<ErrorCodes, ErrorDetails> map;

    static {
        map = new EnumMap<>(ErrorCodes.class);
        map.put(ErrorCodes.ERROR_CODE1, new ErrorDetails(ErrorCodes.ERROR_CODE1, "Bad request - This is error code number one"));
        map.put(ErrorCodes.ERROR_CODE2, new ErrorDetails(ErrorCodes.ERROR_CODE2, "Bad request - This is error code number two"));
        map.put(ErrorCodes.NOT_FOUND_RECIPE, new ErrorDetails(ErrorCodes.NOT_FOUND_RECIPE, "Bad request - Recipe not found"));
        map.put(ErrorCodes.NOT_FOUND_SHOPPING_ITEM, new ErrorDetails(ErrorCodes.NOT_FOUND_SHOPPING_ITEM, "Bad request - Shopping item not found"));
        map.put(ErrorCodes.NOT_FOUND_SHOPPING_LIST, new ErrorDetails(ErrorCodes.NOT_FOUND_SHOPPING_LIST, "Bad request - Shopping list not found"));
        map.put(ErrorCodes.NOT_FOUND_ITEM, new ErrorDetails(ErrorCodes.NOT_FOUND_ITEM, "Bad request - Item not found"));
        map.put(ErrorCodes.INTERNAL_SERVER_GENERIC_ERROR, new ErrorDetails(ErrorCodes.INTERNAL_SERVER_GENERIC_ERROR, "Internal server error"));
        map.put(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE, new ErrorDetails(ErrorCodes.INTERNAL_SERVER_ERROR_WHILE_CREATING_RESOURCE, "Internal server error - There was an error while creating resource"));
        map.put(ErrorCodes.BAD_REQUEST_INVALID_PARAMETER_FORMAT, new ErrorDetails(ErrorCodes.BAD_REQUEST_INVALID_PARAMETER_FORMAT, "Bad request - Format of a parameter is not valid"));
        map.put(ErrorCodes.BAD_REQUEST_MISSING_REQUEST_PARAMETER, new ErrorDetails(ErrorCodes.BAD_REQUEST_MISSING_REQUEST_PARAMETER, "Bad request - Missing request parameters: "));
        map.put(ErrorCodes.BAD_REQUEST_INVALID_CATEGORY, new ErrorDetails(ErrorCodes.BAD_REQUEST_INVALID_CATEGORY, "Bad request - Provided category is not valid"));
        map.put(ErrorCodes.BAD_REQUEST_NO_LIST_PROVIDED, new ErrorDetails(ErrorCodes.BAD_REQUEST_NO_LIST_PROVIDED, "Bad request - Neither recipe nor shopping list was provided for the shopping item"));
    }

    private ErrorMapping() {
    }

    public static String getDescriptionFromCode(ErrorCodes code) {
        return map.get(code).getDescription();
    }

    public static ErrorDetails getErrorDetailsFromCode(ErrorCodes code) {
        return map.get(code);
    }
}
