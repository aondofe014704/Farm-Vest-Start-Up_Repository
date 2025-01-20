package com.visual.status.farmvest.exceptions;

public class FarmVestException extends RuntimeException {
    public FarmVestException(String response) {
        super(response);
    }
}
