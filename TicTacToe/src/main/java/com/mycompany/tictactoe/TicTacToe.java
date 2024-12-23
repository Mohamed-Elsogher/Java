package com.mycompany.tictactoe;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3];
    private boolean playerTurn = true; // true for player, false for AI
    private JLabel statusLabel;

    public TicTacToeAI() {
        // إعداد الواجهة الرسومية
        setTitle("Tic Tac Toe - Classic AI");
        setSize(400, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3));

        // إضافة الأزرار للوحة
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[i][j].setBackground(Color.LIGHT_GRAY); // لون كلاسيكي
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
                board[i][j] = '-';
            }
        }

        statusLabel = new JLabel("Your Turn", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        add(statusLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();
        int row = -1, col = -1;

        // البحث عن الزر الذي تم النقر عليه
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonClicked == buttons[i][j]) {
                    row = i;
                    col = j;
                }
            }
        }

        // إذا كانت الخانة فارغة، يقوم اللاعب بالنقر عليها
        if (board[row][col] == '-' && playerTurn) {
            buttons[row][col].setText("O");
            board[row][col] = 'O';
            playerTurn = false;

            if (checkWinner(board) == 0) {
                statusLabel.setText("AI's Turn");
                makeAIMove(); // AI يلعب
            }
        }
    }

    // حركة الذكاء الاصطناعي
    private void makeAIMove() {
        Move bestMove = findBestMove(board);
        buttons[bestMove.row][bestMove.col].setText("X");
        board[bestMove.row][bestMove.col] = 'X';
        playerTurn = true;

        if (checkWinner(board) != 0 || !isMovesLeft(board)) {
            gameOver();
        } else {
            statusLabel.setText("Your Turn");
        }
    }

    // فحص الفوز أو التعادل
    private int checkWinner(char[][] b) {
        for (int i = 0; i < 3; i++) {
            if (b[i][0] == b[i][1] && b[i][1] == b[i][2] && b[i][0] != '-') {
                return (b[i][0] == 'X') ? +10 : -10;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (b[0][i] == b[1][i] && b[1][i] == b[2][i] && b[0][i] != '-') {
                return (b[0][i] == 'X') ? +10 : -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2] && b[0][0] != '-') {
            return (b[0][0] == 'X') ? +10 : -10;
        }
        if (b[0][2] == b[1][1] && b[1][1] == b[2][0] && b[0][2] != '-') {
            return (b[0][2] == 'X') ? +10 : -10;
        }

        return 0; // إذا لم يكن هناك فائز
    }

    // تحقق من وجود حركات متاحة
    private boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return true;
                }
            }
        }
        return false;
    }

    // دالة Minimax
    private int minimax(char[][] board, boolean isMaximizing) {
        int score = checkWinner(board);

        if (score == 10 || score == -10) return score;
        if (!isMovesLeft(board)) return 0;

        if (isMaximizing) {
            int best = -1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'X';
                        best = Math.max(best, minimax(board, false));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        } else {
            int best = 1000;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        best = Math.min(best, minimax(board, true));
                        board[i][j] = '-';
                    }
                }
            }
            return best;
        }
    }

    // إيجاد أفضل حركة للذكاء الاصطناعي
    private Move findBestMove(char[][] board) {
        int bestVal = -1000;
        Move bestMove = new Move(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'X';
                    int moveVal = minimax(board, false);
                    board[i][j] = '-';

                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    private void gameOver() {
        int result = checkWinner(board);
        if (result == 10) {
            statusLabel.setText("AI Wins!");
        } else if (result == -10) {
            statusLabel.setText("You Win!");
        } else {
            statusLabel.setText("It's a Tie!");
        }

        // تعطيل الأزرار بعد انتهاء اللعبة
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeAI();
    }
}
