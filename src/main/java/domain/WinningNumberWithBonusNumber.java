package domain;

public record WinningNumberWithBonusNumber(
        Lotto winningNumber,
        int bonusNumber
) {

    public WinningNumberWithBonusNumber {
        validateRange(bonusNumber);
        validateDistinct(winningNumber, bonusNumber);
    }

    private void validateRange(int bonusNumber) {
        boolean isValidRange = LottoRule.isLottoRange(bonusNumber);

        if (isValidRange) {
            return;
        }
        throw new IllegalArgumentException("보너스 볼 번호는 "
                + LottoRule.MIN_LOTTO_NUMBER.getValue() + "부터 "
                + LottoRule.MAX_LOTTO_NUMBER.getValue() + " 사이의 숫자여야 합니다. "
                + "입력된 번호: " + bonusNumber);
    }

    private void validateDistinct(Lotto winningNumber, int bonusNumber) {
        if (winningNumber.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(
                    "보너스 번호(" + bonusNumber + ")는 당첨 번호와 중복될 수 없습니다. 당첨 번호: " + winningNumber.getNumbers()
            );
        }
    }
}
