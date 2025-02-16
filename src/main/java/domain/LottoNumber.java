package domain;

public record LottoNumber(int number) {
    public static int LOTTO_MIN_NUMBER = 1;
    public static int LOTTO_MAX_NUMBER = 45;

    public LottoNumber {
        validateLottoNumberRange(number);
    }

    private void validateLottoNumberRange(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("로또 번호는 1 이상 45 이하여야 합니다.");
        }
    }
}
