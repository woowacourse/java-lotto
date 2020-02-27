package lotto.domain;

public class LottoSize {
    private static String NOT_NUMBER_MSG = "잘못된 숫자입니다.";
    private static String NOT_VALIDATE_LOTTO_SIZE_MSG = "%s는 유효하지 않은 로또 개수입니다.";

    private int lottoSize;

    public LottoSize(PaidPrice paidPrice, int lottoSize) {
        validateLottoSize(paidPrice, lottoSize);
        this.lottoSize = lottoSize;
    }

    public LottoSize(PaidPrice paidPrice, String lottoSize) {
        this(paidPrice, validateNumber(lottoSize));
    }

    private static int validateNumber(String number) {
        try {
            return Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(NOT_NUMBER_MSG);
        }
    }

    private void validateLottoSize(PaidPrice paidPrice, int lottoSize) {
        int paidLottoSize = paidPrice.getTotalLottoCount();

        if (paidLottoSize >= lottoSize && lottoSize > 0) {
            return;
        }
        String msg = String.format(NOT_VALIDATE_LOTTO_SIZE_MSG, lottoSize);
        throw new IllegalArgumentException(msg);
    }

    public int getLottoSize() {
        return this.lottoSize;
    }
}
