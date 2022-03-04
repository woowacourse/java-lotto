package lotto.model;

public class LottoMoney {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";
    private static final String ERROR_NOT_POSITIVE = "구입 금액은 양수여야 합니다.";
    private static final String ERROR_NOT_SUFFICIENT_TO_BUY = "로또 구입 금액이 부족합니다.";

    private final int lottoMoney;
    private final int numberOfManualLottos;

    public LottoMoney(int lottoMoney, int numberOfManualLottos) {
        validatePositive(lottoMoney);
        validateUnitPrice(lottoMoney);
        validateCanBuyLottos(lottoMoney, numberOfManualLottos);
        this.lottoMoney = lottoMoney;
        this.numberOfManualLottos = numberOfManualLottos;
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

    private void validateCanBuyLottos(int lottoMoney, int numberOfManualLottos) {
        if (lottoMoney - numberOfManualLottos * LOTTO_PRICE < 0) {
            throw new IllegalArgumentException(ERROR_NOT_SUFFICIENT_TO_BUY);
        }
    }

    int getAutoLottoSize() {
        return lottoMoney / LOTTO_PRICE - numberOfManualLottos;
    }

    public long getLottoMoney() {
        return lottoMoney;
    }
}
