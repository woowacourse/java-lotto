package lotto.util;

public class Validation {

    private static final int ZERO = 0;
    private static final int Lotto_Unit = 1000;

    public static void validatePositiveNumber(String input){
        if(Integer.parseInt(input)< ZERO){
            throw new IllegalArgumentException("음수입니다. 재입력 해주세요");
        }
    }

    public static void validateIntegerNumberFormat(String input) {
        try{
            Integer.parseInt(input);
        }catch (RuntimeException e){
            throw new NumberFormatException("정수만 입력 가능합니다. 재입력 해주세요.");
        }
    }

    public static void validateLottoUnit(String input) {
        if(Integer.parseInt(input)< Lotto_Unit){
            throw new IllegalArgumentException("한장도 구매할수 없습니다. 재입력 해주세요");
        }
    }
}
