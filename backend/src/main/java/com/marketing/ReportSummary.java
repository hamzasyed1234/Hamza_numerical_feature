package com.marketing;

public class ReportSummary {
    private final int totalItems;
    private final int fizzCount;
    private final int buzzCount;
    private final int whizzCount;

    public ReportSummary(int totalItems, int fizzCount, int buzzCount, int whizzCount) {
        this.totalItems  = totalItems;
        this.fizzCount   = fizzCount;
        this.buzzCount   = buzzCount;
        this.whizzCount  = whizzCount;
    }

    public int getTotalItems()  { return totalItems;  }
    public int getFizzCount()   { return fizzCount;   }
    public int getBuzzCount()   { return buzzCount;   }
    public int getWhizzCount()  { return whizzCount;  }

    @Override
    public String toString() {
        return String.format(
            "Total Items: %d | Fizz: %d | Buzz: %d | Whizz: %d",
            totalItems, fizzCount, buzzCount, whizzCount
        );
    }
}
