import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

import static java.awt.Color.black;
import static java.awt.Color.red;
import static java.awt.Color.white;
import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.MAX_VALUE;

/**
 * Created by 中奇 on 2017/9/14.
 */
public class Reversi extends JPanel implements MouseListener {
    final private int ROWS = 8;
    final private int width = 80;

    public int[][] allChess = new int[8][8];

    int x, y;

    public class point {
        int x, y;

        point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Boolean isblack = true; //true-black，false-white
    Boolean canPlay = true;
    public JRadioButton twoPlayer = new JRadioButton("1 vs 1");
    public JRadioButton onePlayer = new JRadioButton("1 vs com");
    public ButtonGroup group = new ButtonGroup();

    public boolean canplay() {
        boolean flag = false;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (allChess[i][j] == 0) flag = true;
            }
        }
        if (flag == false && scoreBlack(allChess) > scoreWhite(allChess)) {
            JOptionPane.showMessageDialog(this, "Black win");
        } else if (flag == false && scoreWhite(allChess) > scoreBlack(allChess)) {
            JOptionPane.showMessageDialog(this, "White win");
        }
        if (scoreWhite(allChess) == 0) {
            JOptionPane.showMessageDialog(this, "Black win");
            flag = false;
        } else if (scoreBlack(allChess) == 0) {
            JOptionPane.showMessageDialog(this, "White win");
            flag = false;
        }

        if (flag = true) canPlay = true;
        else canPlay = false;
        return flag;
    }

    void remove(int i, int j) {
        allChess[i][j] = 0;
    }

    Reversi() {
//        for (int i = 0; i < ROWS; i++) {
//            for (int j = 0; j < ROWS; j++) {
//                allChess[i][j] = 0;
//            }
//        }
        allChess[3][3] = 1;
        allChess[4][4] = 1;
        allChess[4][3] = -1;
        allChess[3][4] = -1;
        add(onePlayer);
        add(twoPlayer);
        group.add(onePlayer);
        group.add(twoPlayer);

    }

    public int scoreBlack(int[][] allChess) {
        int score = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (allChess[i][j] == 1) score++;
            }
        }
        return score;
    }

    public int scoreWhite(int[][] allChess) {
        int score = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (allChess[i][j] == -1) score++;
            }
        }
        return score;
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("draw\r\n");
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, 800, 800);
        g.setColor(black);
        for (int i = 0; i <= ROWS; i++) {
            g.drawLine(80, 80 + width * i, ROWS * width + 80, 80 + width * i);
        }
        for (int i = 0; i <= ROWS; i++) {
            g.drawLine(80 + width * i, 80, 80 + width * i, ROWS * width + 80);
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {


                if (allChess[i][j] == 1) {
                    int tempX = i * width + 120;
                    int tempY = j * width + 120;
                    g.setColor(black);
                    g.fillOval(tempX - 22, tempY - 22, 44, 44);
                    g.setColor(black);
                    g.drawOval(tempX - 22, tempY - 22, 44, 44);
                }

                if (allChess[i][j] == -1) {
                    int tempX = i * width + 120;
                    int tempY = j * width + 120;
                    g.setColor(Color.WHITE);
                    g.fillOval(tempX - 22, tempY - 22, 44, 44);
                    g.setColor(Color.BLACK);
                    g.drawOval(tempX - 22, tempY - 22, 44, 44);
                }
            }
        }
        g.setColor(black);
        Font f1 = new Font("Helvetica", Font.PLAIN, 23);
        g.setFont(f1);
        g.drawString("Player:", 3, 60);
        g.drawString("Score:", 600, 60);
        g.setColor(red);
        g.drawString(scoreBlack(allChess) + " : " + scoreWhite(allChess), 680, 60);
        if (isblack) {
            g.setColor(black);
            g.fillOval(80, 40, 26, 26);
            g.setColor(black);
            g.drawOval(80, 40, 26, 26);
        } else {
            g.setColor(white);
            g.fillOval(80, 40, 26, 26);
            g.setColor(black);
            g.drawOval(80, 40, 26, 26);
        }
    }

    public void ChangeColor1(int x1, int y1, int x2, int y2, int[][] allChess) {
        if (x1 == x2) {
            for (int i = Math.min(y1, y2); i < (Math.max(y1, y2)); i++) {
                allChess[x1][i] = allChess[x2][y2];
            }
        }
    }

    public void ChangeColor2(int x1, int y1, int x2, int y2, int[][] allChess) {
        if (y1 == y2) {
            for (int i = Math.min(x1, x2); i < (Math.max(x1, x2)); i++) {
                allChess[i][y1] = allChess[x2][y2];
            }
        }
    }

    public void ChangeColor3(int x1, int y1, int x2, int y2, int[][] allChess) {
        if (x2 - x1 == y2 - y1) {
            int i = Math.min(y1, y2);
            int j = Math.min(x1, x2);
            while (i < (Math.max(y1, y2)) && j < (Math.max(x1, x2))) {
                allChess[j][i] = allChess[x2][y2];
                i++;
                j++;
            }
        }
    }

    public void ChangeColor4(int x1, int y1, int x2, int y2, int[][] allChess) {
        if (x2 - x1 == y1 - y2) {
            int i = Math.min(y1, y2);
            int j = Math.max(x1, x2);
            while (i < (Math.max(y1, y2)) && j > (Math.min(x1, x2))) {
                allChess[j][i] = allChess[x2][y2];
                i++;
                j--;
            }
        }
    }

    public boolean CanUse(int x, int y, int[][] allChess, boolean isblack) {
        boolean flag = false;
        int col;
        if (isblack) col = 1;
        else col = -1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (i == x && allChess[i][j] == col && Math.abs(y - j) != 1) {
                    boolean a = false;
                    for (int n = 1 + Math.min(j, y); n < Math.max(j, y); n++) {
                        if (allChess[i][n] == allChess[i][j] || allChess[i][n] == 0) a = true;
                    }
                    if (a == false) {
                        flag = true;
                    }
                } else if (j == y && allChess[i][j] == col && Math.abs(x - i) != 1) {
                    boolean a = false;
                    for (int m = 1 + Math.min(i, x); m < Math.max(i, x); m++) {
                        if (allChess[m][j] == allChess[i][j] || allChess[m][j] == 0) a = true;
                    }
                    if (a == false) {
                        flag = true;
                    }
                } else if (x - i == y - j && allChess[i][j] == col && Math.abs(x - i) != 1 && Math.abs(y - j) != 1) {
                    boolean a = false;
                    int m = 1 + Math.min(i, x);
                    int n = 1 + Math.min(j, y);
                    while (m < Math.max(i, x) && n < Math.max(j, y)) {
                        if (allChess[m][n] == allChess[i][j] || allChess[m][n] == 0) {
                            a = true;
                        }
                        m++;
                        n++;
                    }
                    if (a == false) {
                        flag = true;
                    }
                } else if (x - i == j - y && allChess[i][j] == col && Math.abs(x - i) != 1 && Math.abs(y - j) != 1) {
                    boolean a = false;
                    int m = 1 + Math.min(i, x);
                    int n = Math.max(j, y) - 1;
                    while (m < Math.max(i, x) && n > Math.min(j, y)) {
                        if (allChess[m][n] == allChess[i][j] || allChess[m][n] == 0) {
                            a = true;
                        }
                        m++;
                        n--;
                    }
                    if (a == false) {
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    public void Use(int x, int y, int[][] allChess, boolean isblack) {
        int col;
        if (isblack) col = 1;
        else col = -1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (i == x && allChess[i][j] == col) {
                    boolean a = false;
                    for (int n = 1 + Math.min(j, y); n < Math.max(j, y); n++) {
                        if (allChess[i][n] == allChess[i][j] || allChess[i][n] == 0) a = true;
                    }
                    if (a == false) {
                        ChangeColor1(x, y, i, j, allChess);
                    }
                } else if (j == y && allChess[i][j] == col) {
                    boolean a = false;
                    for (int m = 1 + Math.min(i, x); m < Math.max(i, x); m++) {
                        if (allChess[m][j] == allChess[i][j] || allChess[m][j] == 0) a = true;
                    }
                    if (a == false) {
                        ChangeColor2(x, y, i, j, allChess);
                    }
                } else if (x - i == y - j && allChess[i][j] == col) {
                    boolean a = false;
                    int m = 1 + Math.min(i, x);
                    int n = 1 + Math.min(j, y);
                    while (m < Math.max(i, x) && n < Math.max(j, y)) {
                        if (allChess[m][n] == allChess[i][j] || allChess[m][n] == 0) {
                            a = true;
                        }
                        m++;
                        n++;
                    }
                    if (a == false) {

                        ChangeColor3(x, y, i, j, allChess);
                    }
                } else if (x - i == j - y && allChess[i][j] == col) {
                    boolean a = false;
                    int m = 1 + Math.min(i, x);
                    int n = Math.max(j, y) - 1;
                    while (m < Math.max(i, x) && n > Math.min(j, y)) {
                        if (allChess[m][n] == allChess[i][j] || allChess[m][n] == 0) {
                            a = true;
                        }
                        m++;
                        n--;
                    }
                    if (a == false) {
                        ChangeColor4(x, y, i, j, allChess);
                    }
                }
            }
        }
    }

    public int canUse1(int x, int y) {
        int num = 0;
        int col;
        if (isblack) col = 1;
        else col = -1;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (i == x && allChess[i][j] == col && Math.abs(y - j) != 1) {
                    boolean a = false;
                    for (int n = 1 + Math.min(j, y); n < Math.max(j, y); n++) {
                        if (allChess[i][n] == allChess[i][j] || allChess[i][n] == 0) a = true;
                    }
                    if (a == false) {
                        num += Math.abs(j - y);
                    }
                } else if (j == y && allChess[i][j] == col && Math.abs(x - i) != 1) {
                    boolean a = false;
                    for (int m = 1 + Math.min(i, x); m < Math.max(i, x); m++) {
                        if (allChess[m][j] == allChess[i][j] || allChess[m][j] == 0) a = true;
                    }
                    if (a == false) {
                        num += Math.abs(i - x);
                    }
                } else if (x - i == y - j && allChess[i][j] == col && Math.abs(x - i) != 1 && Math.abs(y - j) != 1) {
                    boolean a = false;
                    int m = 1 + Math.min(i, x);
                    int n = 1 + Math.min(j, y);
                    while (m < Math.max(i, x) && n < Math.max(j, y)) {
                        if (allChess[m][n] == allChess[i][j] || allChess[m][n] == 0) {
                            a = true;
                        }
                        m++;
                        n++;
                    }
                    if (a == false) {
                        num += Math.abs(j - y);
                    }
                } else if (x - i == j - y && allChess[i][j] == col && Math.abs(x - i) != 1 && Math.abs(y - j) != 1) {
                    boolean a = false;
                    int m = 1 + Math.min(i, x);
                    int n = Math.max(j, y) - 1;
                    while (m < Math.max(i, x) && n > Math.min(j, y)) {
                        if (allChess[m][n] == allChess[i][j] || allChess[m][n] == 0) {
                            a = true;
                        }
                        m++;
                        n--;
                    }
                    if (a == false) {
                        num += Math.abs(j - y);
                    }
                }
            }
        }
        return num;
    }

    //    public int n = 0;
    Set set = new HashSet();
    Integer num = MIN_VALUE;


    public point AI(int[][] chess) {
        point coordinate = new point(0, 0);
        int valueWhite = MIN_VALUE;

        if (canPlay) {
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < ROWS; j++) {

                    int[][] newChess2 = new int[ROWS][ROWS];
                    for (int in = 0; in < ROWS; in++) {
                        for (int jn = 0; jn < ROWS; jn++) {
                            newChess2[in][jn] = chess[in][jn];
                        }
                    }

                    if (CanUse(i, j, chess, false)) {
                        point cW = new point(0, 0);
                        //copy chess
                        int[][] newChess = new int[ROWS][ROWS];
                        for (int in = 0; in < ROWS; in++) {
                            for (int jn = 0; jn < ROWS; jn++) {
                                newChess[in][jn] = chess[in][jn];
                            }
                        }
                        newChess[i][j] = -1;
                        Use(i, j, newChess, false);
                        int valueBlack = MIN_VALUE;
                        point cB = new point(0, 0);
                        for (int i1 = 0; i1 < ROWS; i1++) {
                            for (int j1 = 0; j1 < ROWS; j1++) {
                                int[][] newChess1 = new int[ROWS][ROWS];
                                for (int in = 0; in < ROWS; in++) {
                                    for (int jn = 0; jn < ROWS; jn++) {
                                        newChess1[in][jn] = newChess[in][jn];
                                    }
                                }
                                if (CanUse(i1, j1, newChess1, true)) {
                                    newChess1[i1][j1] = 1;
                                    Use(i1, j1, newChess1, true);
                                    if (getValue(newChess1, true) > valueBlack) {
                                        valueBlack = getValue(newChess1, true);
                                        cB.x = i1;
                                        cB.y = j1;
                                        cW.x = i;
                                        cW.y = j;
                                    }
                                }
                            }
                        }
                        newChess2[cW.x][cW.y] = -1;
                        Use(cW.x, cW.y, newChess2, false);

                        newChess2[cB.x][cB.y] = 1;
                        Use(cB.x, cB.y, newChess2, true);

                        if (getValue(newChess2, false) > valueWhite) {
                            valueWhite = getValue(newChess2, false);
                            coordinate.y = j;
                            coordinate.x = i;
                        }
//                        chess[i][j] = 0;
                    }
                }
            }
        }
        return coordinate;
    }

    int getValue(int[][] a, Boolean flag) {
        int[][] b = {{100, -5, 10, 5, 5, 10, -5, 100},
                {-5, -45, 1, 1, 1, 1, -45, -5},
                {10, 1, 3, 2, 2, 3, 1, 10},
                {5, 1, 2, 1, 1, 2, 1, 5},
                {5, 1, 2, 1, 1, 2, 1, 5},
                {10, 1, 3, 2, 2, 3, 1, 10},
                {-5, -45, 1, 1, 1, 1, -45, -5},
                {100, -5, 10, 5, 5, 10, -5, 100}};
        int value = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < ROWS; j++) {
                if (flag == true && a[i][j] == 1) value += b[i][j];
                if (flag == false && a[j][j] == -1) value += b[i][j];
            }
        }
        return value;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        canplay();
        if (canPlay) {
            int point_x = e.getX() - 80;
            int point_y = e.getY() - 80;
            int r = ROWS * width;
            //if (point_x >= 60 && point_x <= ROWS * chess_width + 100 && point_y >= 60 && point_y <= ROWS * chess_width + 100) {
            System.out.println("OK");
            if (point_x < r && point_y < r) {
                x = point_x / width;
                y = point_y / width;
            }


//                chessX[countX++] = x;
//                chessY[countY++] =
            if (twoPlayer.isSelected()) {
                if (isblack && CanUse(x, y, allChess, isblack) && allChess[x][y] == 0) {
                    allChess[x][y] = 1;
                    Use(x, y, allChess, isblack);
                    isblack = false;
                } else if (CanUse(x, y, allChess, isblack) && allChess[x][y] == 0) {
                    allChess[x][y] = -1;
                    Use(x, y, allChess, isblack);
                    isblack = true;
                }
            } else if (onePlayer.isSelected()) {
                System.out.println("onePlayer");
                if (isblack && CanUse(x, y, allChess, isblack) && allChess[x][y] == 0) {

                    allChess[x][y] = 1;
                    Use(x, y, allChess, isblack);
                    isblack = false;
                } else if (isblack == false) {
                    int[][] newChess = new int[8][8];
                    for (int i = 0; i < ROWS; i++) {
                        for (int j = 0; j < ROWS; j++) {
                            newChess[i][j] = allChess[i][j];
                        }
                    }

                    point c = new point(0, 0);
                    point x = AI(newChess);
                    int x1 = x.x;
                    int y1 = x.y;
                    allChess[x1][y1] = -1;
                    Use(x1, y1, allChess, isblack);
                    isblack = true;
                }
            }
            this.repaint();
        }

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

}
