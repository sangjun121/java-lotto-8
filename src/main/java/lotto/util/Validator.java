package lotto.util;

public final class Validator {

    private Validator() {
    }

    public static boolean isNullOrBlank(String target) {
        return target == null || target.trim().isEmpty();
    }
}
