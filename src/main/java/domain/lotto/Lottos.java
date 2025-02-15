package domain.lotto;

import static domain.lotto.Lotto.LOTTO_PRICE;
import static domain.lotto.Lotto.MAX_LOTTO_NUMBER;
import static domain.lotto.Lotto.MAX_LOTTO_SIZE;
import static domain.lotto.Lotto.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Lottos {

    private final List<Lotto> lottos;

    private Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottos create(int money) {
        int quantity = money / LOTTO_PRICE;

        List<Lotto> generatedLottos = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            List<Integer> lottoNumbers = generateRandomNumbers(random);
            generatedLottos.add(
                    new Lotto(lottoNumbers)
            );
        }

        return new Lottos(generatedLottos);
    }

    private static List<Integer> generateRandomNumbers(Random random) {
        List<Integer> lottoNumbers = new ArrayList<>();

        while (lottoNumbers.size() < MAX_LOTTO_SIZE) {
            int number = random.nextInt(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER + 1);
            addNumberIfUnique(lottoNumbers, number);
        }

        lottoNumbers.sort(Comparator.naturalOrder());

        return lottoNumbers;
    }

    private static void addNumberIfUnique(List<Integer> lottoNumbers, int number) {
        if (!lottoNumbers.contains(number)) {
            lottoNumbers.add(number);
        }
    }

    public int getQuantity() {
        return lottos.size();
    }

    public List<String> getLottoNumbers() {
        return lottos.stream()
                .map(Lotto::getBallsString)
                .toList();
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
