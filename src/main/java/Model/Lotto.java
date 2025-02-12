package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lotto {

    private List<Integer> lottoNumber;

    public Lotto(){
        this.lottoNumber = new ArrayList<>();
        createLotto();
    }

    private void createLotto(){
        Random random = new Random();
        for (int i = 0; i < 6; i++){
            this.lottoNumber.add(random.nextInt(45) + 1);
        }
    }

    public List<Integer> getLottoNumber() {
        return lottoNumber;
    }
}
