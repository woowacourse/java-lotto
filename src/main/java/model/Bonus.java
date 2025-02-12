package model;

public class Bonus {

    private Integer number;

    public Bonus(final String input) {
        validateInteger(input);
        this.number = Integer.parseInt(input);
    }

    private void validateInteger(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
    }
}
