package lotto.model;

public class LottoMoney {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양수여야 합니다.";

    private final int lottoMoney;

    public LottoMoney(int lottoMoney) {
        validatePositive(lottoMoney);
        validateUnitPrice(lottoMoney);
        this.lottoMoney = lottoMoney;
    }

    private void validatePositive(int lottoMoney) {
        if (lottoMoney <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateUnitPrice(int lottoMoney) {
        if (lottoMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    int getLottoSize() {
        return lottoMoney / LOTTO_PRICE;
    }

    float divide(long totalWinningMoney) {
        return totalWinningMoney / (float)lottoMoney;
    }
}
