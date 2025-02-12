package model;

public record Number(int value) {

    public Number {
        if (value <= 0 || value > 45) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하여야 합니다.");
        }
    }
}
