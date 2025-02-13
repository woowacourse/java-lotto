public class LottoMachine {

    private final int ticket;

    private static final int LOTTO_PRICE = 1000;

    public LottoMachine(Price price) {
        this.ticket = price.getValue() / LOTTO_PRICE;
    }

    public Lottos generateLotto() {
        Lottos lottos = new Lottos();
        for (int i = 0; i < ticket; i++) {
            Lotto lotto = new Lotto(RandomGenerator.generateUniqueRandomNumbers(6, 1, 45));
            lottos.addLotto(lotto);
        }
        return lottos;
    }

    public int getTicket() {
        return ticket;
    }
}
