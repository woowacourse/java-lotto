package service;

import constant.LottoConstants;
import domain.Lotto;
import java.util.Collections;
import java.util.List;
import validator.Validator;

public class LottoMaker {

    private static LottoMaker instance;

    private final NumberGenerate numberGenerate;

    private LottoMaker(NumberGenerate numberGenerate) {
        this.numberGenerate = numberGenerate;
    }

    public static LottoMaker getInstance() {
        if (instance == null) {
            instance = new LottoMaker(LottoNumberGenerator.getInstance());
        }
        return instance;
    }

    public Lotto createLotto() {
        List<Integer> numbers = numberGenerate.generateRandomInRange(
                LottoConstants.LOTTO_NUMBER_START, LottoConstants.LOTTO_NUMBER_END, LottoConstants.LOTTO_COUNT
        );
        Validator.isDuplicates(numbers);
        Collections.sort(numbers);
        return new Lotto(numbers);
    }
}
