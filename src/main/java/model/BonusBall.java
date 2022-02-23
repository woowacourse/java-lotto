package model;

public class BonusBall {

    public BonusBall(String number) {
        validateInputNumberBlank(number);
    }

    private void validateInputNumberBlank(String number) {
        if (number == null || number.isBlank()) {
            throw new IllegalArgumentException();
        }
    }
}
