package lotto.domain;

public class Number {
    private static final String NOT_NUMBER_MSG = "정수로 입력하셔야 합니다.";
    protected int number;

    public Number(String inputNumber) {
        validateNumber(inputNumber);
        this.number = Integer.parseInt(inputNumber);
    }

    public static void validateNumber(String invalidInputMoney) {
        try{
            Integer.parseInt(invalidInputMoney);
        } catch (NumberFormatException e){
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }
}
