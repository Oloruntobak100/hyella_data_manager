package com.hyella.hyellapaymentservice.gatewayservice.middleware;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler( Exception.class )
    public ResponseEntity< ? > handleGlobalException( Exception ex, WebRequest request ){
        // Log the exception
        System.out.println( "============= ERROR ============================" );
        System.err.println( "Exception: " + ex.getMessage() );
        System.err.println( "Stack Trace:" );
        StackTraceElement[] stackTrace = ex.getStackTrace();
        for( StackTraceElement element : stackTrace ){
            System.err.println( "\t" + element.toString() );
        }
        System.out.println( "============= ERROR =============================" );
        ErrorResponse errorResponse = new ErrorResponse( "An unexpected error occurred. " + ex.getMessage() );
        return new ResponseEntity<>( errorResponse, HttpStatus.INTERNAL_SERVER_ERROR );
    }
}

class ErrorResponse {
    private String message;
    private String status = "error";
    private Object errorBody;

    public ErrorResponse( String message ){
        this.message = message;
    }

    public ErrorResponse( String message, Object body ){
        this.message = message;
        this.errorBody = body;
    }

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }

    public void setMessage( String message ){
        this.message = message;
    }
}
