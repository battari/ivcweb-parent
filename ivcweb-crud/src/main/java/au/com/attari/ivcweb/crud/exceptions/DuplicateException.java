package au.com.attari.ivcweb.crud.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
    public DuplicateException(String tableName, String id) {
        super(new StringBuffer(tableName)
                .append(" with ")
                .append(id)
                .append(" already exists.").toString());
    }
}
