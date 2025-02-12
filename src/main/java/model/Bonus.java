package model;

public class Bonus {

    private Integer number;

    public static Bonus of(final String input, final Lotto lotto) {
        validateInteger(input);
        return new Bonus(Integer.parseInt(input), lotto);
    }

    public Bonus(final Integer number, final Lotto lotto) {
        validateRange(number);
        validateDuplicateWithLotto(number, lotto);
        this.number = number;
    }

    private static void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
    }

    private void validateRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("보너스 번호는 1부터 45사이여야 합니다.");
        }
    }

    private void validateDuplicateWithLotto(Integer number, Lotto lotto) {
        if (lotto.getNumbers().contains(number)) {
            throw new IllegalArgumentException("보너스 번호는 당첨 번호와 중복되지 말아야 합니다.");
        }
    }

    public Integer getNumber() {
        return number;
    }
}
