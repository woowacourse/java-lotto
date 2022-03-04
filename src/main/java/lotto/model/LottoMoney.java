package lotto.model;

public class LottoMoney {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양수여야 합니다.";
    private static final String ERROR_NOT_SUFFICIENT_TO_BUY = "로또 구입 금액이 부족합니다.";

    private final long lottoMoney;
    private final int numberOfManualLottos;

    LottoMoney(long lottoMoney, int numberOfManualLottos) {
        validatePositive(lottoMoney);
        validateUnitPrice(lottoMoney);
        validateCanBuyLottos(lottoMoney, numberOfManualLottos);
        this.lottoMoney = lottoMoney;
        this.numberOfManualLottos = numberOfManualLottos;
    }

    private void validatePositive(long lottoMoney) {
        if (lottoMoney <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE);
        }
    }

    private void validateUnitPrice(long lottoMoney) {
        if (lottoMoney % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    private void validateCanBuyLottos(long lottoMoney, int numberOfManualLottos) {
        if (lottoMoney - (long)numberOfManualLottos * LOTTO_PRICE < 0) {
            throw new IllegalArgumentException(ERROR_NOT_SUFFICIENT_TO_BUY);
        }
    }

    int getAutoLottoSize() {
        return Math.toIntExact(lottoMoney / LOTTO_PRICE - numberOfManualLottos);
    }

    long getLottoMoney() {
        return lottoMoney;
    }
}
