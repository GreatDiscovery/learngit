package com.leetcod;


/**
 * @author gavin
 * @date 2019/1/24 8:23
 */
public class Demo_980 {
    int ans;
    int[][] grid;
    int tr, tc;
    int[] dr = {0, -1, 0, 1};
    int[] dc = {1, 0, -1, 0};
    int R, C;

    public int uniquePathsIII(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;

        int todo = 0;
        int sr = 0, sc = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] != -1) {
                    todo++;
                }
                if (grid[i][j] == 1) {
                    sr = i;
                    sc = j;
                }
                if (grid[i][j] == 2) {
                    tr = i;
                    tc = j;
                }
            }
        }
        ans = 0;
        dfs(sr, sc, todo);
        return ans;
    }

    private void dfs(int r, int c, int todo) {
        todo--;

        if (todo < 0) return;
        if (r == tr && c == tc) {
            if (todo == 0) {
                ans++;
                return;
            }
        }
        grid[r][c] = 3;
        for (int k = 0; k < 4; k++) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (grid[nr][nc] % 2 == 0) {
                    dfs(nr, nc, todo);
                }
            }
        }
        grid[r][c] = 0;

    }
}
