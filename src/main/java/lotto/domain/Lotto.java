package lotto.domain;

public class Lotto {

    private ChoiceNumber choiceNumber;

    public Lotto(ChoiceNumber choiceNumber) {
        this.choiceNumber = choiceNumber;
    }

    public ChoiceNumber getChoiceNumber() {
        return choiceNumber;
    }
}
