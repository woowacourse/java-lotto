package lotto.domain;

public class WinnerLotto {
    private final LottoNumbers winnerNumbers;
    private final LottoNumber bonusNumber;

    public WinnerLotto(final LottoNumbers winnerNumbers, final LottoNumber bonusNumber) {
        this.winnerNumbers = winnerNumbers;
        this.bonusNumber = bonusNumber;
    }

    /**
     * // NOTICE 매개변수 사용에대한 의견 대립 List<LottoNumbers> - 필요한 것만 받을 수 있다. 즉 객체 전체를 넘겨주지 않아도 되기 때문에 Lotto 객체를 은닉화할 수 있다.
     * Lotto lotto - 강력한 타입 제한, Lotto의 객체를 통해 로또의 List<LottoNumbers>의 제약을 걸 수 있음.
     */
    public long getMatchCount(Lotto lotto) {
        return winnerNumbers.getLottoNumbers()
                .stream()
                .filter(lotto::contains)
                .count();
    }

    public boolean hasBonus(Lotto lotto) {
        return lotto.contains(bonusNumber);
    }

    public static void validateInputWinnerNumbers(String input) {
        if (input == null || input.isBlank() || input.endsWith(", ")) {
            throw new IllegalArgumentException("잘못된 입력입니다. 이와 같은 형태로 작성해주세요.(ex. 1, 2, 3, 4, 5, 6)");
        }
    }

    public static void validateBonusNumbers(LottoNumbers winnerNumbers, LottoNumber bonusNumber) {
        if (winnerNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 넘버가 당첨 번호에 중복됩니다.");
        }
    }
}
