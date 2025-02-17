package lotto.domain;

public class RandomNumber {
    public int generateRandomNumber(int start, int end) {
        return (int) ((Math.random() * end) + start);
    }

}
