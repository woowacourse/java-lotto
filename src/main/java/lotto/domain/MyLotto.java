package lotto.domain;

import java.util.ArrayList;
import java.util.List;

public class MyLotto {
    private final List<Lotto> myLotto;

    MyLotto(String[] numbers, int round) {
        this.myLotto = getHandLottos(numbers);
        addMyLotto(round);
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

    private void addMyLotto(int round) {
        NumberGenerator numberGenerator = NumberGenerator.create();

        for (int i = 0; i < round; i++) {
            List<Number> list = numberGenerator.getNumbers();
            myLotto.add(new Lotto(list));
        }
    }
}
