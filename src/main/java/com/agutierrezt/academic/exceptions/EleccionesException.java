package com.agutierrezt.academic.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EleccionesException extends RuntimeException {
    private int statusCode;
    private String message;
    private Date transactionDate;

    public EleccionesException(String message, int statusCode, Date transactionDate) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.transactionDate = transactionDate;
    }
}
