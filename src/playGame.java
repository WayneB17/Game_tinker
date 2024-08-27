import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class playGame implements ActionListener{

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel stat_panel_Player = new JPanel();
    JPanel stat_panel_Enemy = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[4];
    JLabel[] player_stat = new JLabel[6];
    JLabel[] enemy_stat = new JLabel[4];
    boolean playerTurn;
    Random random = new Random();

    Character player = new Character(100,10,10,10,0, 0, 1);
    Enemy basic_enemy = new Enemy(40, 10, 10, 10, 0, 20);
    int level_up = 100;

    boolean gameOver;


    playGame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,900);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Verdana", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("GAME");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,frame.getWidth(),100);

        button_panel.setLayout(new GridLayout(2,2));
        button_panel.setBackground(new Color(150,150,150));
        button_panel.setBounds(0,600,frame.getWidth(),200);

        stat_panel_Player.setLayout(new GridLayout(2,3));
        stat_panel_Player.setBounds(0,350,frame.getWidth(),250);
        stat_panel_Player.setBackground(Color.lightGray);

        stat_panel_Enemy.setLayout(new GridLayout(2,2));
        stat_panel_Enemy.setBounds(0,100,frame.getWidth(),250);
        stat_panel_Enemy.setBackground(Color.lightGray);

        beginGame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < 4; i++) {
                if (e.getSource() == buttons[i]) {

                    //Player
                    if (playerTurn) {
                        player.active_defense = 0;
                        player_stat[1].setText("Defence: " + player.active_defense);


                        if (buttons[i].getText().equals("Attack")) {
                            basic_enemy.curr_health = basic_enemy.curr_health - (player.attack - basic_enemy.active_defense);
                            enemy_stat[0].setText("Health: " + basic_enemy.curr_health);
                            if (basic_enemy.curr_health <= 0) {
                                basic_enemy.curr_health = 0;
                                enemy_stat[0].setText("Health: " + basic_enemy.curr_health);
                                player.exp = player.exp + basic_enemy.exp;
                                player_stat[5].setText("Exp: " + player.exp + "/" + level_up);
                                player_wins();
                            }

                        } else if (buttons[i].getText().equals("Defend")) {
                            player.active_defense = player.defense;
                            player_stat[1].setText("Defence: " + player.active_defense);
                        } else if (buttons[i].getText().equals("Heal")) {
                            if (player.curr_health < player.Max_health) {
                                player.curr_health = player.curr_health + 20;
                                player_stat[0].setText("Health: " + player.curr_health);
                            }
                            if (player.curr_health > player.Max_health) {
                                player.curr_health = player.Max_health;
                                player_stat[0].setText("Health: " + player.curr_health);
                            } else {
                                textfield.setText("Full health");
                            }

                        } else if (buttons[i].getText().equals("Run")) {
                            //resets battle (later on this will start a new battle with new enemy);

                        }

                        if(!gameOver) {
                            playerTurn = false;
                            textfield.setText("Enemy Turn");
                        }else {

                            play_again();

                        }

                    } else {

                        //ENEMY

                        basic_enemy.active_defense = 0;
                        enemy_stat[1].setText("Defence: " + basic_enemy.active_defense);

                        if (buttons[i].getText().equals("Attack")) {
                            player.curr_health = player.curr_health - (basic_enemy.attack - player.active_defense);
                            player_stat[0].setText("Health: " + player.curr_health);
                            if (player.curr_health <= 0) {
                                enemy_wins();
                            }

                        } else if (buttons[i].getText().equals("Defend")) {
                            basic_enemy.active_defense = basic_enemy.defense;
                            enemy_stat[1].setText("Defence: " + basic_enemy.active_defense);
                        } else if (buttons[i].getText().equals("Heal")) {
                            if (basic_enemy.curr_health < basic_enemy.Max_health) {
                                basic_enemy.curr_health = basic_enemy.curr_health + 20;
                                enemy_stat[0].setText("Health: " + basic_enemy.curr_health);
                            }
                            if (basic_enemy.curr_health > basic_enemy.Max_health) {
                                basic_enemy.curr_health = basic_enemy.Max_health;
                                enemy_stat[0].setText("Health: " + basic_enemy.curr_health);
                            } else {
                                textfield.setText("Full health");
                            }

                        } else if (buttons[i].getText().equals("Run")) {
                            //resets battle (later on this will start a new battle with new enemy);

                        }
                        if(!gameOver) {
                            playerTurn = true;
                            textfield.setText("Player Turn");
                        }else{
                            play_again();

                        }
                    }
                }
            }
        }



    public void beginGame() {
        for(int i = 0; i<6; i++){
            player_stat[i] = new JLabel();
            stat_panel_Player.add(player_stat[i]);
            player_stat[i].setFont(new Font("Verdana", Font.BOLD, 20));
            player_stat[i].setFocusable(false);
        }

        player_stat[0].setText("Health: " + player.Max_health);
        player_stat[1].setText("Defence: " + player.active_defense);
        player_stat[2].setText("Level: " + player.level);
        player_stat[3].setText("Attack: " + player.attack);
        player_stat[4].setText("Speed: " + player.speed);
        player_stat[5].setText("Exp: " + player.exp +"/" +level_up);


        for(int i = 0; i<4; i++){
            enemy_stat[i] = new JLabel();
            stat_panel_Enemy.add(enemy_stat[i]);
            enemy_stat[i].setFont(new Font("Verdana", Font.BOLD, 20));
            enemy_stat[i].setFocusable(false);
        }

        enemy_stat[0].setText("Health: " + basic_enemy.Max_health);
        enemy_stat[1].setText("Defence: " + basic_enemy.active_defense);
        enemy_stat[2].setText("Attack: " + basic_enemy.attack);
        enemy_stat[3].setText("Speed: " + basic_enemy.speed);

        for(int i = 0; i<4; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Verdana", Font.BOLD, 20));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        buttons[0].setText("Attack");
        buttons[1].setText("Defend");
        buttons[2].setText("Heal");
        buttons[3].setText("Run");

        title_panel.add(textfield);
        frame.add(title_panel);
        frame.add(stat_panel_Player);
        frame.add(stat_panel_Enemy);
        frame.add(button_panel);

        for(int i = 0; i<4; i++){
            buttons[i].setEnabled(false);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        if(player.speed > basic_enemy.speed){
            playerTurn = true;
            textfield.setText("Player turn");
        }

        else if( player.speed == basic_enemy.speed){
            if(random.nextInt(2) == 0){
                playerTurn = true;
                textfield.setText("Player turn");
            }
            else{
                playerTurn = false;
                textfield.setText("Enemy turn");
            }
        }

        else{
            playerTurn = false;
            textfield.setText("Enemy turn");
        }
        for(int i = 0; i<4; i++){
            buttons[i].setEnabled(true);
        }
    }

    public void player_wins(){

        if (player.exp >= level_up){
            player.exp = player.exp - level_up;
            player.level = player.level + 1;
            level_up = 100 + ((player.level -1)  * 20);
            player_stat[2].setText("Level: " + player.level);
            player_stat[5].setText("Exp: " + player.exp + "/" + level_up);
            textfield.setText("Player levelled up!");
        }

        textfield.setText("Player wins!");
        gameOver = true;

    }


    public void enemy_wins(){

        textfield.setText("Enemy wins!");
        gameOver = true;
    }

    public void play_again(){

        basic_enemy.curr_health = basic_enemy.Max_health;
        player.curr_health = player.Max_health;

        enemy_stat[0].setText("Health: " + basic_enemy.curr_health);
        player_stat[0].setText("Health: " + player.curr_health);

        if(player.speed > basic_enemy.speed){
            playerTurn = true;
            textfield.setText("Player turn");
        }

        else if( player.speed == basic_enemy.speed){
            if(random.nextInt(2) == 0){
                playerTurn = true;
                textfield.setText("Player turn");
            }
            else{
                playerTurn = false;
                textfield.setText("Enemy turn");
            }
        }

        else{
            playerTurn = false;
            textfield.setText("Enemy turn");
        }

        gameOver = false;
    }


}
