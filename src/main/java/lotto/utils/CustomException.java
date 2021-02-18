package lotto.utils;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        throw new RuntimeException(message);
    }
}
