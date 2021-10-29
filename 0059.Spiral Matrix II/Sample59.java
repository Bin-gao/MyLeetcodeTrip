package array;

public class Sample59 {
    public static void main(String[] args) {
        new Solution59().generateMatrix(1);
    }
}


class Solution59 {
        public int[][] generateMatrix(int n) {
            int[][] result = new int[n][n];
            int loop  = n / 2;
            int count = 1;
            int offset = 1;
            int startX = 0;
            int startY = 0;

            while ( loop > 0 ){
                //上
                for ( ; startY < (n - offset) ; startY++ ) {
                    result[startX][startY] = count++;
                }
                //右
                for ( ; startX < (n - offset) ; startX++ ) {
                    result[startX][n - offset] = count++;
                }

                for ( ; startY > offset-1 ; startY-- ) {
                    result[n - offset][startY] = count++;
                }

                for ( ; startX > offset-1 ; startX-- ) {
                    result[startX][startY] = count++;
                }

                loop--;
                startX++;
                startY++;
                offset++;
            }
            if ( (n & 1) == 0 ) return result;
            result[startX][startY] = count;
            return result;

        }
    }