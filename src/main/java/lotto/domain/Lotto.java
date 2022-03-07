package lotto.domain;

public class Lotto {
    private final PickedNumbers pickedNumbers;

    public Lotto(PickedNumbers pickedNumbers) {
        this.pickedNumbers = pickedNumbers;
    }

    public PickedNumbers getPickedNumbers() {
        return pickedNumbers;
    }
}
