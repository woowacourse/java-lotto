package lotto.view.inputview;

public class PriceParser {

    private static final String ERROR_NON_INPUT = "입력이 없습니다.";

    public static int getLottoAmount(String input) {

        if(input == null || input.isEmpty()){
            throw new NullPointerException(ERROR_NON_INPUT);
        }

        return Integer.parseInt(input);
    }
}
