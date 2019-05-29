package lotto.domain;

public class PriceFactory {
    public static Price generatePrice(int price){
        return new Price(price);
    }
}
