package lotto.model;

import java.util.List;

public class Lottos {

    private static final int UNIT_PRICE = 1_000;
    private final List<Lotto> lottos;

    public Lottos(final List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static void issue(final int money) {
        try {
            if (money % UNIT_PRICE != 0) {
                throw new IllegalArgumentException("천원 단위로 입력해 주세요.");
            }
            int count = money / UNIT_PRICE;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 금액 형식입니다.");
        }
    }

}
