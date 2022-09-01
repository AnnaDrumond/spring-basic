package com.io.github.annadrumond.springbasic.services.exceptions;

//Exceção personalizada para casos em que ocorre uma busca sem resultados na BD
//RuntimeException, neste caso o compilador não obriga a tratar

public class ResourceNotFoundException extends RuntimeException{

    //Vaiu receber o id/pk do objeto que está sendo buscado
    public ResourceNotFoundException(Object pk){
        super("Resource Not Found. Pk: " + pk);
    }

}
