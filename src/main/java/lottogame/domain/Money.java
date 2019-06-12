package lottogame.domain;

public class Money {
    private static final int IN_VALID_PRICE = -1;
    private static final int PERCENTAGE = 100;
    private static final int ONE_LOTTO_PRICE = 1000;

    private final int price;

    public Money(int price) {
        this.price = price;
    }

    public static Money generate(int inputPrice) {
        checkValidPrice(inputPrice);
        return new Money(inputPrice);
    }

    private static void checkValidPrice(int price) {
        if (!((price % ONE_LOTTO_PRICE == 0) && (price >= ONE_LOTTO_PRICE))) {
            throw new InvalidLottoPriceException("구입금액을 다시 입력해 주세요.");
        }
    }

    public int getNumberOfTicket() {
        return price / ONE_LOTTO_PRICE;
    }

    long rateOf(double profits) {
        return (long) (profits / price) * PERCENTAGE;
    }

    public boolean checkInValidNumber(int numberOfManualLotto) {
        if(getNumberOfTicket() < numberOfManualLotto){
            throw new IllegalArgumentException(getNumberOfTicket()+"개 이하만 가능합니다.");
        }
        return false;
    }
}
