package domain.player;

import domain.Lotto.Lotto;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.LottoGenerator;
import domain.Result;

import java.util.ArrayList;
<<<<<<< HEAD
<<<<<<< HEAD
import java.util.Collections;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
import java.util.Collections;
>>>>>>> 3158ddf (refactor : 일급 컬렉션의 List 불변 객체로 만들기)
import java.util.List;

public class Player {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;

=======
>>>>>>> f3df2f7 (refactor : 상수 접근지정자 변경)
    private final Money money;
    private final List<Lotto> lottos;
<<<<<<< HEAD

    public Player(int money) {
        this.money = new Money(money);
        this.lottos = new ArrayList<>();
    }

    public boolean canBuyLotto() {
        return money.isBiggerThanLottoPrice();
    }

    public void purchaseLotto(Lotto lotto) {
        money.deductMoney();
        lottos.add(lotto);
    }

<<<<<<< HEAD

=======
=======
    private static final int MINIMUM_PURCHASE_AMOUNT = 1000;

>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
    private final Money money;
    private List<Lotto> lottos;
=======
>>>>>>> a879dd3 (feat : 구매한 모든 로또의 결과를 기록하는 클래스 추가)

    public Player(int money) {
        this.money = new Money(money);
        this.lottos = new ArrayList<>();
    }

    public boolean canBuyLotto() {
        return money.isBiggerThanLottoPrice();
    }

    public void purchaseLotto(Lotto lotto) {
        money.deductMoney();
        lottos.add(lotto);
    }

<<<<<<< HEAD
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======

>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
=======
>>>>>>> 4022ea6 (refactor: 메서드 위치 변경)
    public List<Result> judgeAll(WinningLotto winningLotto) {
        List<Result> result = new ArrayList<>();
        for (Lotto lotto : lottos) {
            result.add(lotto.judge(winningLotto));
        }
        return result;
    }

    public double calculateIncomeRate(double totalIncome) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        return totalIncome / (lottos.size() * MINIMUM_PURCHASE_AMOUNT);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos);
=======
        return totalIncome / money.getAmount();
=======
        return totalIncome / (lottos.size() * MINIMUM_PURCHASE_AMOUNT);
>>>>>>> 440c90c (refactor : Player 로또 구매 역할 분리)
=======
        return totalIncome / (lottos.size() * Money.MINIMUM_PURCHASE_AMOUNT);
>>>>>>> f3df2f7 (refactor : 상수 접근지정자 변경)
    }

    public List<Lotto> getLottos() {
<<<<<<< HEAD
        return lottos;
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
=======
        return Collections.unmodifiableList(lottos);
>>>>>>> 3158ddf (refactor : 일급 컬렉션의 List 불변 객체로 만들기)
    }
}
