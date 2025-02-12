package service;

import domain.AnswerLotto;
import domain.Lotto;
import java.util.List;

public class LottoService {
    public AnswerLotto getAnswerLotto(List<Integer> selectedNumbers, int bonus) {
        Lotto answerLotto = new Lotto(selectedNumbers);
        return new AnswerLotto(answerLotto, bonus);
    }
}
