public record WinningNumberWithBonusNumber(
        Lotto winningNumber,
        int bonusNumber
) {

    public WinningNumberWithBonusNumber {
        validate(winningNumber, bonusNumber);
    }

    private void validate(Lotto winningNumber, int bonusNumber) {
        validateDistinct(winningNumber, bonusNumber);
    }

    private void validateDistinct(Lotto winningNumber, int bonusNumber) {
        if (winningNumber.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException();
        }
    }
}
