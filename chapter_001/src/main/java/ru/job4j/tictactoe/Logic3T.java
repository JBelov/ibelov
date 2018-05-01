package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    private boolean checkDiagonalX() {
        boolean right, left;
        right = true;
        left = true;
        for (int i = 0; i < table.length; i++) {
            right &= (table[i][i].hasMarkX());
            left &= (table[table.length - i - 1][i].hasMarkX());
        }
        return (right || left);
    }

    private boolean checkLinesX() {
        boolean cols, rows;
        for (int col = 0; col < table.length; col++) {
            cols = true;
            rows = true;
            for (int row = 0; row < table.length; row++) {
                cols &= (table[col][row].hasMarkX());
                rows &= (table[row][col].hasMarkX());
            }
            if (cols || rows) {
                return true;
            }
        }

        return false;
    }

    private boolean checkDiagonalO() {
        boolean right, left;
        right = true;
        left = true;
        for (int i = 0; i < table.length; i++) {
            right &= (table[i][i].hasMarkO());
            left &= (table[table.length - i - 1][i].hasMarkO());
        }
        return (right || left);
    }

    private boolean checkLinesO() {
        boolean cols, rows;
        for (int col = 0; col < table.length; col++) {
            cols = true;
            rows = true;
            for (int row = 0; row < table.length; row++) {
                cols &= (table[col][row].hasMarkO());
                rows &= (table[row][col].hasMarkO());
            }
            if (cols || rows) {
                return true;
            }
        }
        return false;
    }

    public boolean isWinnerX() {
        return checkDiagonalX() || checkLinesX();
    }

    public boolean isWinnerO() {
        return checkDiagonalO() || checkLinesO();
    }

    public boolean hasGap() {
        Boolean result = false;
        for (Figure3T[] rows : table) {
            for (Figure3T point : rows) {
                if (!point.hasMarkO() && !point.hasMarkX()) {
                    result = true;
                }
            }
        }
        return result;
    }
}
