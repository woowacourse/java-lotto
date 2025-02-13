public record WinningNumberWithBonusNumber(
        Lotto winningNumber,
        int bonusNumber
) {

    public WinningNumberWithBonusNumber(Lotto winningNumber, int bonusNumber) {
        validate(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
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
