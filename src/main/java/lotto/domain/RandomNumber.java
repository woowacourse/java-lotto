package lotto.domain;

public class RandomNumber {

    public int generateRandomNumber() {
        return (int) ((Math.random() * 45) + 1);
    }
}
