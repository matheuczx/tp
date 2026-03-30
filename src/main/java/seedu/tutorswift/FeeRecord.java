package seedu.tutorswift;

import java.time.YearMonth;
import java.util.ArrayList;

/**
 * Tracks the per-lesson fee and paid months for a student.
 */
public class FeeRecord {
    private int feePerLesson;
    private final ArrayList<YearMonth> paidMonths;

    public FeeRecord() {
        this.feePerLesson = 0;
        this.paidMonths = new ArrayList<>();
    }

    public int getFeePerLesson() {
        return feePerLesson;
    }

    public void setFeePerLesson(int feePerLesson) {
        this.feePerLesson = feePerLesson;
    }

    public ArrayList<YearMonth> getPaidMonths() {
        return paidMonths;
    }

    public boolean isPaidForMonth(YearMonth month) {
        return paidMonths.contains(month);
    }

    /**
     * Marks the given month as paid. Ignores if already marked.
     */
    public void markPaid(YearMonth month) {
        if (!paidMonths.contains(month)) {
            paidMonths.add(month);
        }
    }

    /**
     * Removes the given month from paid list. Ignores if not present.
     */
    public void markUnpaid(YearMonth month) {
        paidMonths.remove(month);
    }

    /**
     * Converts fee data to save format.
     * Example: "50:2026-01,2026-02" or "50:NONE"
     */
    public String toSaveFormat() {
        if (paidMonths.isEmpty()) {
            return feePerLesson + ":NONE";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paidMonths.size(); i++) {
            sb.append(paidMonths.get(i).toString());
            if (i < paidMonths.size() - 1) {
                sb.append(",");
            }
        }
        return feePerLesson + ":" + sb;
    }

    /**
     * Parses from save format string.
     * Example input: "50:2026-01,2026-02" or "50:NONE"
     */
    public static FeeRecord fromSaveFormat(String raw) {
        String[] splitParts = raw.split(":", 2);
        int fee = Integer.parseInt(splitParts[0]);
        FeeRecord record = new FeeRecord();
        record.setFeePerLesson(fee);
        if (!splitParts[1].equals("NONE")) {
            String[] months = splitParts[1].split(",");
            for (String m : months) {
                record.markPaid(YearMonth.parse(m.trim()));
            }
        }
        return record;
    }

    @Override
    public String toString() {
        String feeStr = (feePerLesson == 0) ? "Not set" : "$" + feePerLesson + "/lesson";

        if (paidMonths.isEmpty()) {
            return "Fee: " + feeStr + " | No payments recorded";
        }

        StringBuilder paidStr = new StringBuilder();
        for (int i = 0; i < paidMonths.size(); i++) {
            YearMonth ym = paidMonths.get(i);
            paidStr.append(ym.getMonth()).append(" ").append(ym.getYear()).append(": [PAID]");
            if (i < paidMonths.size() - 1) {
                paidStr.append(", ");
            }
        }

        return "Fee: " + feeStr + " | " + paidStr.toString();
    }
}
