package domain;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottos {

    private final List<Lotto> lottos = new ArrayList<>();

    private Lottos(int money) {
        int quantity = money / 1000;

        for (int i = 0; i < quantity; i++) {
            List<Integer> lottoNumbers = generateRandomNumbers();
            lottos.add(new Lotto(lottoNumbers));
        }
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> lottoNumbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(1, 46);
            lottoNumbers.add(number);
        }
        return lottoNumbers;
    }

    public static Lottos of(int money) {
        return new Lottos(money);
    }

    public int getQuantity() {
        return lottos.size();
    }

    public List<String> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getNumbers)
                .toList();
    }


}
