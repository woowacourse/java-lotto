package lotto.exception;;

public class LottoPiecesException extends LottoException {

    public LottoPiecesException(String errorMessage) {
        super(errorMessage);
        System.out.println(errorMessage);
    }
}
