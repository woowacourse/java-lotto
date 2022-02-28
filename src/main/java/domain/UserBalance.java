package domain;

public class UserBalance {
    private static final String INVALID_BALANCE_EXCEPTION_MESSAGE = "구입금액은 1000원 이상이어야 하며 1000원 미만일 수 없습니다.";
    private static final String INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE = "수동 입력 가능 횟수는 0번 이상 최대 구입 가능 이하여야 합니다.";
    public static final int LOTTO_PRICE = 1000;

    private final int userBalance;
    private final int manualLottoCount;
    private final int autoLottoCount;

    public UserBalance(int userBalance, int manualLottoCount) {
        this.userBalance = validateBalance(userBalance);
        this.manualLottoCount = validateLottoCount(manualLottoCount);
        this.autoLottoCount = calculateAutoLottoCount();
    }

    private int validateBalance(int balance) {
        if (!(balance >= LOTTO_PRICE && balance % LOTTO_PRICE == 0)) {
            throw new IllegalArgumentException(INVALID_BALANCE_EXCEPTION_MESSAGE);
        }
        return balance;
    }

    private int validateLottoCount(int lottoCount) {
        if (lottoCount < 0 || lottoCount > userBalance / LOTTO_PRICE) {
            throw new IllegalArgumentException(INVALID_LOTTO_COUNT_EXCEPTION_MESSAGE);
        }
        return lottoCount;
    }

    private int calculateAutoLottoCount() {
        return userBalance / LOTTO_PRICE - manualLottoCount;
    }

    public int getUserBalance() {
        return userBalance;
    }

    public int getManualLottoCount() {
        return manualLottoCount;
    }

    public int getAutoLottoCount() {
        return autoLottoCount;
    }
}
