package domain;

public class LottoNumber {
    public static final int LOTTO_NUMBER_START = 1;
    public static final int LOTTO_NUMBER_END = 45;

    private int num;

    public LottoNumber(int num) {
        validate(num);
        this.num = num;
    }

    private void validate(int num) {
        validateNumberScope(num);
    }

    private void validateNumberScope(int num) {
            if (num < LOTTO_NUMBER_START || num > LOTTO_NUMBER_END) {
                throw new IllegalArgumentException("로또 번호는" + LOTTO_NUMBER_START+"에서 부터" + LOTTO_NUMBER_END +"까지의 수 입니다");
            }
    }

    public int getNum() {
        return num;
    }
}
