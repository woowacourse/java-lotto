package domain;

public class WinningNumber {
    private static Lotto winningNumbers;
    private static LottoNumber bonusNumber;

    public static void inputWinningNumbers(final String[] numbers) {
        winningNumbers = LottoFactory.createOneManualLotto(numbers);
    }

    public static void inputBonusNumber(String bonus) {
        checkNotNumber(bonus);
        bonus = bonus.trim();
        bonusNumber = AllLottoNumbers.get(Integer.parseInt(bonus));
        checkDuplicatedLottoNumber();
    }

    public static void countWinningLotto(final Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()){
            LottoRank rank = LottoRank.findRank(countWinningMatch(lotto), isBonusMatch(lotto));
            LottoResult.addWinningRankCount(rank);
        }
    }

    private static int countWinningMatch(final Lotto myLotto) {
        checkLottoNull(myLotto);
        return winningNumbers.countMatchNumbers(myLotto);
    }

    private static boolean isBonusMatch(final Lotto myLotto) {
        checkLottoNull(myLotto);
        return myLotto.contains(bonusNumber);
    }

    private static void checkLottoNull(final Lotto myLotto) {
        if (myLotto == null) {
            throw new NullPointerException("비교할 로또가 없습니다.");
        }
    }

    private static void checkDuplicatedLottoNumber() {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨 번호와 보너스 번호는 중복될 수 없습니다.");
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