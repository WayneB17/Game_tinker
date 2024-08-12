public class Main {
    final int Max_HP = 200;
    final int Max_speed = 100;
    final int Max_attack = 50;
    final int Max_defense = 30;
    final int Max_level = 20;
    public static void main(String[] args) {
        Main gameStat = new Main();
        Character player = new Character();
        Enemy basic_enemy = new Enemy(120, 10, 10, 10, 20);

        player.Max_health = 100;
        player.curr_health = player.Max_health;
        player.defense = 10;
        player.speed = 10;
        player.attack = 10;
        player.exp = 0;
        player.level = 1;
        int level_up = 80 + (20 * player.level);

        System.out.println(player.curr_health + " / " + player.Max_health);

    }

}
