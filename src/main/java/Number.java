public record Number(int value) {
    public static int MIN = 1;
    public static int MAX = 45;

     public Number {
        validate(value);
     }

    public static Number from(String rawInput) {
        try {
            return new Number(Integer.parseInt(rawInput));
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
