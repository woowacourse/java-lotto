package model;

public class LottoMachine {

    private final int ticket;

    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_NUMBER_START = 1;
    private static final int LOTTO_NUMBER_END = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public LottoMachine(Price price) {
        this.ticket = price.getValue() / LOTTO_PRICE;
    }

    public Lottos generateLottos() {
        Lottos lottos = new Lottos();
        for (int i = 0; i < ticket; i++) {
            Numbers randomNumbers = new Numbers(RandomGenerator.generateUniqueRandomNumbers(LOTTO_NUMBER_COUNT, LOTTO_NUMBER_START, LOTTO_NUMBER_END));
            Lotto lotto = new Lotto(randomNumbers);
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    public int getTicket() {
        return ticket;
    }
}
