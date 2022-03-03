package lotto.client;

import java.util.List;

public interface InputClient {
    String input(String prompt);

    List<String> inputRepeat(String prompt, int count);
}
