package domain;

import java.util.Objects;

public class WinningNumber {
    public static final int MIN_LOTTO_NUMBER = 1;
    public static final int MAX_LOTTO_NUMBER = 45;
    private static Lotto winningNumbers;
    private static LottoNumber bonusNumber;

    public static void inputWinningNumbers(final String[] numbers) {
        winningNumbers = LottoFactory.createOneManualLotto(numbers);
    }

    public static void inputBonusNumber(String bonus) {
        checkNotNumber(bonus);
        bonus = bonus.trim();
        int bonusIntegerValue = Integer.parseInt(bonus);
        checkLottoNumberRange(bonusIntegerValue);
        bonusNumber = AllLottoNumbers.get(Integer.parseInt(bonus));
        checkDuplicatedLottoNumber();
    }

    public static int countWinningMatch(final Lotto targetLotto) {
        Objects.requireNonNull(targetLotto);
        return winningNumbers.countMatchNumbers(targetLotto);
    }

    public static boolean isBonusMatch(final Lotto targetLotto) {
        Objects.requireNonNull(targetLotto);
        return targetLotto.contains(bonusNumber);
    }

    private static void checkDuplicatedLottoNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private static void checkLottoNumberRange(int bonusIntegerValue) {
        if (bonusIntegerValue < MIN_LOTTO_NUMBER || bonusIntegerValue > MAX_LOTTO_NUMBER){
            throw new IllegalArgumentException("로또 번호는 1부터 45까지 수여야 합니다.");
        }
    }

    private static void checkNotNumber(final String number) {
        try {
            Integer.parseInt(number);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("보너스 넘버는 숫자여야 합니다. 입력한 문자 : %s", number));
        }
    }
}