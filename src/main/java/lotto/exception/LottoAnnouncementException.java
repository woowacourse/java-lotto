package lotto.exception;

public class LottoAnnouncementException extends LottoException {

    public LottoAnnouncementException(String errorMessage) {
        super(errorMessage);
        System.out.println(errorMessage);
    }
}
