package com.desafio.GestaoEscola.handlerException;

import com.desafio.GestaoEscola.utils.ResponseBody;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice(annotations = RestController.class)
public class GlobalResponseEntityException extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseBody resposta = this.construirBaseResposta( "Metódo Http não foi encontrado.", HttpStatus.valueOf(status.value()), ex.getMessage() );
        return ResponseEntity.status( status ).body( resposta );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseBody resposta = this.construirBaseResposta( "Metódo Http não foi encontrado.", HttpStatus.valueOf(status.value()), ex.getMessage() );
        return ResponseEntity.status( status ).body( resposta );
    }

    @Override
    protected ResponseEntity<Object> handleErrorResponseException(ErrorResponseException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return super.handleErrorResponseException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        ResponseBody resposta = this.construirBaseResposta( "Não foi possível atender a resposta.", HttpStatus.valueOf(statusCode.value()), ex.getMessage() );
        return ResponseEntity.status( HttpStatus.valueOf(statusCode.value()) ).body( resposta );
    }

    protected ResponseEntity<Object> messageConversionException(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ResponseBody resposta = this.construirBaseResposta( "Não foi possível atender a resposta.", status, ex.getMessage() );
        return ResponseEntity.status( status ).body( resposta );
    }


    private ResponseBody construirBaseResposta(String message, HttpStatus status, Object...data )
    {
        ResponseBody resposta = new ResponseBody();
        resposta.setMensagem( message );
        resposta.setStatus( status );
        if ( data.length > 0 )
            resposta.setData( data[0] );
        return resposta;
    }

}
