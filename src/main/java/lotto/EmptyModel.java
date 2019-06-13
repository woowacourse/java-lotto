package lotto;

import java.util.HashMap;
import java.util.Map;

public class EmptyModel {
    private static Map<String, Object> emptyModel;

    private EmptyModel() {  }

    public static Map<String, Object> get() {
        if (emptyModel == null) {
            emptyModel = new HashMap<>();
        }
        return emptyModel;
    }
}
