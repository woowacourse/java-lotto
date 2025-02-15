package domain;

public record WinningNumberWithBonusNumber(
        Lotto winningNumber,
        int bonusNumber
) {

    public WinningNumberWithBonusNumber {
        validateDistinct(winningNumber, bonusNumber);
    }

    private void validateDistinct(Lotto winningNumber, int bonusNumber) {
        if (winningNumber.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }
}
