package lotto.model.lottostore;

public enum Price {
    LOTTO_TICKET_PRICE(1_000),
    FREE_TICKET_PRICE(0);

    private int price;

    Price(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
