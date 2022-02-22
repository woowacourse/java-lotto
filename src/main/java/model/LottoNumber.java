package model;

public class LottoNumber {
    private final int number;

    public LottoNumber(GenerateStrategy generateStrategy) {
        int generatedNumber = generateStrategy.generateNumber();
        this.number = checkNumber(generatedNumber);
    }

    public LottoNumber(int number) {
        this.number = number;
    }

    private int checkNumber(int number) {
        if (isNotCorrectNumber(number)) {
            throw new RuntimeException();
        }
        return number;
    }

    private boolean isNotCorrectNumber(int number) {
        return !(number <= 45 && number > 0);
    }

    public int value() {
        return number;
    }
}
