package Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Lotto {

    private final int LOTTO_COUNT = 6;
    private List<Integer> lottoNumber;

    public Lotto(){
        this.lottoNumber = new ArrayList<>();
        createLotto();
    }

    private void createLotto(){
        Random random = new Random();
        int count = LOTTO_COUNT;
        while (count > 0) {
            int randomNumber = random.nextInt(45) + 1;
            if (this.lottoNumber.contains(randomNumber)) {
                continue;
            }
            this.lottoNumber.add(randomNumber);
            count--;
        }
        lottoNumber.sort(Comparator.naturalOrder());
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
