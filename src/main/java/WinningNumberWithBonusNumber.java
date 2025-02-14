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
            throw new IllegalArgumentException("당첨번호 " + winningNumber + "는 이미 존재하는 번호입니다. 보너스 번호와 당첨번호는 중복될 수 없습니다.");
        }
    }
}
