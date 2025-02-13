package domain;

public class WinningNumber {
    private final Lotto numbers;
    private final int bonusNumber;

    private WinningNumber(Lotto numbers, int bonusNumber) {
        this.numbers = numbers;
        this.bonusNumber= bonusNumber;
    }

    public static WinningNumber create(Lotto numbers, int bonusNumber){
        return new WinningNumber(numbers,bonusNumber);
    }

    public Lotto getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
