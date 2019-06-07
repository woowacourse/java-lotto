package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class MyLotto {
    private final List<Lotto> myLotto;

    MyLotto(String[] numbers, int round) {
        this.myLotto = provideMyLotto(numbers, round);
    }

    public static MyLotto create(String[] numbers, int round) {
        return new MyLotto(numbers, round);
    }

    public int getSize() {
        return myLotto.size();
    }

    public Lotto getIndexByLotto(int index) {
        return myLotto.get(index);
    }

    private List<Lotto> provideMyLotto(String[] numbers, int round) {
        List<Lotto> myLottos = getHandLottos(numbers);
        myLottos.addAll(getMyLotto(round));
        return myLottos;
    }

    private static List<Lotto> getHandLottos(String[] handleNumbers) {
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < handleNumbers.length; i++) {
            String[] oneNumbers = handleNumbers[i].split(",");
            lottos.add(new Lotto(addLottoNumbers(i, oneNumbers)));
        }

        return lottos;
    }

    private static List<Number> addLottoNumbers(int i, String[] oneNumbers) {
        List<Number> numbers = new ArrayList<>();

        for (String oneNumber : oneNumbers) {
            numbers.add(new Number(Integer.parseInt(oneNumber)));
        }
        return numbers;
    }

    private List<Lotto> getMyLotto(int round) {
        List<Lotto> myLotto = new ArrayList<>();

        for (int i = 0; i < round; i++) {
            myLotto.add(new Lotto(NumberGenerator.create().getNumbers()));
        }

        return myLotto;
    }
}
