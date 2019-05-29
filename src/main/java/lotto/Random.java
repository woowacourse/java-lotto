package lotto;

public class Random {
    private static final int MAX_NUM = 45;
    private static final int MIN_NUM = 1;

    public static int generateLottoNumber() {
        return (int)(Math.random() * MAX_NUM + MIN_NUM);
    }



}
