package lotto;

public class Money {
    private int price;

    public Money(String price) {
        validMoney(price);
        validPositive(price);
        this.price = Integer.parseInt(price);
    }

    private void validPositive(String price) {
        if (Integer.parseInt(price)<0){
            throw new IllegalArgumentException("Positive Error");
        }
    }

    private void validMoney(String price) {
        try {
            Integer.parseInt(price);
        } catch (Exception error) {
            throw new IllegalArgumentException("Space Error");
        }
    }


    public int getPrice(){
        return this.price;
    }
}