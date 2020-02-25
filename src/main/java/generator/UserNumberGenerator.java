package generator;

import view.InputView;

import java.util.List;

public class UserNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> create() {
        return InputView.inputNumbers();
    }
}
