import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class playGame implements ActionListener{

    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[4];
    boolean playerTurn;
    Random random = new Random();

    Character player = new Character(100,20,20,10,0, 0, 1);
    Enemy basic_enemy = new Enemy(120, 10, 10, 10, 0, 20);
    int level_up = 80 + (20 * player.level);


    playGame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25,25,25));
        textfield.setForeground(new Color(25,255,0));
        textfield.setFont(new Font("Verdana", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("GAME");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,600,100);

        button_panel.setLayout(new GridLayout(2,2));
        button_panel.setBackground(new Color(150,150,150));
        button_panel.setBounds(0,0,600,200);

       for(int i = 0; i<4; i++){
           buttons[i] = new JButton();
           buttons[i].setPreferredSize(new Dimension(0,100));
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
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel, BorderLayout.PAGE_END);

        beginGame();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=0; i<4; i++){
            if(e.getSource()==buttons[i]){

                //Player

                if(playerTurn){
                    //if player pushes button, preform action, set actions for the 4 buttons.
                    player.active_defense = 0;

                    if (buttons[i].getText()=="Attack"){
                        basic_enemy.curr_health = basic_enemy.curr_health - (player.attack - basic_enemy.active_defense);
                        if(basic_enemy.curr_health <= 0){
                            textfield.setText("Player wins");
                            break;
                        }

                    }
                    else if (buttons[i].getText()=="Defend"){
                        player.active_defense = player.defense;
                    }
                    else if (buttons[i].getText() == "Heal"){
                        if(player.curr_health < player.Max_health) {
                            player.curr_health = player.curr_health + 20;
                        }
                        if(player.curr_health > player.Max_health)
                            player.curr_health = player.Max_health;

                        else{
                            textfield.setText("Full health");
                        }

                    }
                    else if (buttons[i].getText()=="Run"){
                        //resets battle (later on this will start a new battle with new enemy);

                    }

                    playerTurn = false;
                    textfield.setText("Enemy Turn");
                    System.out.println("Enemy health: " + basic_enemy.curr_health);


                }
                else{

                //ENEMY

                    basic_enemy.active_defense = 0;

                    if (buttons[i].getText()=="Attack"){
                        player.curr_health = player.curr_health - (basic_enemy.attack - player.active_defense);
                        if(player.curr_health <= 0){
                            textfield.setText("Enemy wins");
                            break;
                        }

                    }
                    else if (buttons[i].getText()=="Defend"){
                        basic_enemy.active_defense = basic_enemy.defense;
                    }
                    else if (buttons[i].getText() == "Heal"){
                        if(basic_enemy.curr_health < basic_enemy.Max_health) {
                            basic_enemy.curr_health = basic_enemy.curr_health + 20;
                        }
                        if(basic_enemy.curr_health > basic_enemy.Max_health)
                            basic_enemy.curr_health = basic_enemy.Max_health;

                        else{
                            textfield.setText("Full health");
                        }

                    }
                    else if (buttons[i].getText()=="Run"){
                        //resets battle (later on this will start a new battle with new enemy);

                    }

                    playerTurn = true;
                    textfield.setText("Player Turn");
                    System.out.println("Player health: " + player.curr_health);
                }
            }
        }
    }

    public void beginGame() {
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
    }

    //methods for game


}
