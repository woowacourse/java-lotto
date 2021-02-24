package lotto;

public class Money {

    public static final String POSITIVE_ERROR_MESSAGE = "음수는 불가능합니다.";
    public static final String SPACE_ERROR_MESSAGE = "공백은 불가능합니다.";
    private int price;

    public Money(String price) {
        validMoney(price);
        validPositive(price);
        this.price = Integer.parseInt(price);
    }

    private void validPositive(String price) {
        if (Integer.parseInt(price) < 0) {
            throw new IllegalArgumentException(POSITIVE_ERROR_MESSAGE);
        }
    }

    private void validMoney(String price) {
        try {
            Integer.parseInt(price);
        } catch (Exception error) {
            throw new IllegalArgumentException(SPACE_ERROR_MESSAGE);
        }
    }

    public int getPrice(){
        return this.price;
    }
}