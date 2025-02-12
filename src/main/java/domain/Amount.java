package domain;

import exception.ExceptionMessage;

public class Amount {
    public static final int LOTTO_PRICE = 1000;

    private int amount;

    public Amount(String inputPrice) {
        int price = validateIsInteger(inputPrice);
        validateIsPositive(price);
        validatePerPrice(price);
        this.amount = price;
    }

    public boolean compareAmount(int amount) {
        return this.amount > amount;
    }

    public int getAmount() {
        return amount;
    }

    private void validateIsPositive(int price) {
        if (price < 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_POSITIVE.getMessage());
        }
    }

    private int validateIsInteger(String inputPrice) {
        try{
            return Integer.parseInt(inputPrice);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_INTEGER.getMessage());
        }

    }

    private void validatePerPrice(int price) {
        if(price % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_UNIT_PRICE.getMessage());
        }
    }


}
