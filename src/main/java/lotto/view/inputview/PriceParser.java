package lotto.view.inputview;

public class PriceParser {

    private static final String ERROR_NULL_OR_NO_INPUT = "입력이 없습니다.";
    private static final String ERROR_MINIMUM_LIMIT_PRICE = "구매 금액은 1000원 이상입니다.";

    public static int getLottoAmount(String input) {

        if(input == null || input.isEmpty()){
            throw new NullPointerException(ERROR_NULL_OR_NO_INPUT);
        }

        int lottoAmount = Integer.parseInt(input);

        if(lottoAmount < 1000){
            throw new IllegalArgumentException(ERROR_MINIMUM_LIMIT_PRICE);
        }

        if(lottoAmount % 1000 != 0){
            throw new IllegalArgumentException("구매 금액은 1,000원 단위 입니다.");
        }

        return lottoAmount / 1000;
    }
}
