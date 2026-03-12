package com.votemais.urnadigital.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() { super("Não Encontrado.");}

    public NotFoundException(String message) {super(message);}
}
