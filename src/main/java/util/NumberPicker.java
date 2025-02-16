package util;

import domain.Numbers;

public interface NumberPicker {

    Numbers pickUnique(int start, int end, int count);
}
