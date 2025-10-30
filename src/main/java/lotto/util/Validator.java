package lotto.util;

public final class Validator {

    private Validator() {
    }

    public static boolean isNullOrBlank(String target) {
        return target == null || target.trim().isEmpty();
    }

    public static boolean isLessThan(int target, int threshold) {
        return target < threshold;
    }

    public static boolean isDivisibleBy(int target, int divisor) {
        return target % divisor == 0;
    }

    public static boolean startsOrEndsWith(String target, String substring) {
        return target.startsWith(substring) || target.endsWith(substring);
    }

    public static boolean containsConsecutiveSubstring(String target, String substring) {
        String doubleSub = substring + substring;
        return target.contains(doubleSub);
    }

    public static boolean containsCharExactCount(String target, String character, int expectedCount) {
        char searchChar = character.charAt(0);

        long count = target.chars()
                .filter(c -> c == searchChar)
                .count();

        return count == expectedCount;
    }

}
