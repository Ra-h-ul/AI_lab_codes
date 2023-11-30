import java.util.Arrays;

public class NQ{
    public static void main(String[] args) {
        char[][] mat = new char[4][4];
        mat[0][1] = 'q';
        BacktrackingOptimized b = new BacktrackingOptimized(mat);
        boolean ans = b.backtrack(1);
        System.out.println(Arrays.deepToString(mat));
        System.out.println(ans);
    }
}

class BacktrackingOptimized {
    char[][] matrix;
    private int n;

    BacktrackingOptimized(char[][] m) {
        matrix = m;
        n = matrix.length;
    }

    public boolean backtrack(int r) {
        if (r == n)
            return true;
        for (int i = 0; i < n; i++) {
            if (!isAttack(r, i)) {
                matrix[r][i] = 'q';
                if (backtrack(r + 1)) {
                    return true;
                }
                matrix[r][i] = '\0';
            }
        }
        return false;
    }

    private boolean isAttack(int r, int c) {
        for (int i = 0; i < n; i++) {
            if (matrix[r][i] == 'q')
                return true;
        }
        for (int i = 0; i < n; i++) {
            if (matrix[i][c] == 'q')
                return true;
        }
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--) {
            if (matrix[i][j] == 'q')
                return true;
        }
        for (int i = r + 1, j = c + 1; i < n && j < n; i++, j++) {
            if (matrix[i][j] == 'q')
                return true;
        }
        for (int i = r - 1, j = c + 1; i >= 0 && j < n; i--, j++) {
            if (matrix[i][j] == 'q')
                return true;
        }
        for (int i = r + 1, j = c - 1; i < n && j >= 0; j--, i++) {
            if (matrix[i][j] == 'q')
                return true;
        }
        return false;
    }
}