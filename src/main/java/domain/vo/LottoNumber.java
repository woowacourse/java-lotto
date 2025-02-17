package domain.vo;

public record LottoNumber(int value) {
    public static int MIN = 1;
    public static int MAX = 45;

     public LottoNumber {
        validate(value);
     }

    public static LottoNumber from(String rawInput) {
        try {
            return new LottoNumber(Integer.parseInt(rawInput));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void validate(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException();
        }
    }
}
