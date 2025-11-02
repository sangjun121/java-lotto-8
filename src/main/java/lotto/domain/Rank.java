package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Rank {
    FIRST(6, false, 2000000000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == this.getMatchCount();
        }
    },
    SECOND(5, true, 30000000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 5 && isBonus;
        }
    },
    THIRD(5, false, 1500000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 5 && !isBonus;
        }
    },
    FOURTH(4, false, 50000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 4;
        }
    },
    FIFTH(3, false, 5000, true) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count == 3;
        }
    },
    NONE(-1, false, 0, false) {
        @Override
        public boolean matches(int count, boolean isBonus) {
            return count < 3;
        }
    };

    private final int matchCount;
    private final boolean isBonus;
    private final int prizeMoney;
    private final boolean isWinning;

    Rank(int matchCount, boolean isBonus, int prizeMoney, boolean isWinning) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.prizeMoney = prizeMoney;
        this.isWinning = isWinning;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public boolean isWinning() {
        return isWinning;
    }

    abstract boolean matches(int count, boolean isBonus);

    public static Rank valueOf(int matchCount, boolean isBonus) {
        for (Rank rank : values()) {
            if (rank.matches(matchCount, isBonus)) {
                return rank;
            }
        }

        return NONE;
    }

    public static List<Rank> winningRanks() {
        return Arrays.stream(Rank.values())
                .filter(Rank::isWinning)
                .sorted(Comparator.comparing(Rank::getPrizeMoney))
                .toList();
    }
}

