import java.util.*;

public class Enemy {
    int Max_health;
    int curr_health;
    int speed;
    int attack;
    int defense;
    int active_defense;
    int exp;
    public Enemy(int h, int s, int a, int d, int ad, int e) {
        Max_health = h;
        curr_health = Max_health;
        speed = s;
        attack = a;
        defense = d;
        active_defense = ad;
        exp = e;

    }

}
