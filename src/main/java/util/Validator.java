package util;

public class Validator {
    public static void inputValidatorIsNull(String input){
        if(input.isBlank()) {
            throw new IllegalArgumentException("공백 및 null 값은 허용하지 않습니다");
        }
    }
    public static void inputValidatorParseInt(String input){
        try{
            Integer.parseInt(input);
        }catch(NumberFormatException nfe){
            throw new IllegalArgumentException("숫자가 아닌 입력은 불가능 합니다.");
        }
    }
}
