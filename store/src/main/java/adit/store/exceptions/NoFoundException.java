package adit.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.ALREADY_REPORTED, reason="Object Already exist")
public class NoFoundException extends  Exception{

    public NoFoundException(String message){
        super(message);
    }
}
