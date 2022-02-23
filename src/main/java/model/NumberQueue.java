package model;

import java.util.Set;

public interface NumberQueue {

    boolean hasNext();

    int get();

    Set<Integer> get(int quantity);
}
