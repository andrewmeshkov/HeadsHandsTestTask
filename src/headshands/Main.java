package headshands;

import java.util.*;

public class Main {
	
	static boolean checkArgs(int attack, int defend, int health, int leftDamage, int rigthDamage) {
		boolean result = true;
		result &= (1 <= attack && attack <= 30);
		result &= (1 <= defend && defend <= 30);
		result &= (health > 0);
		result &= (rigthDamage > leftDamage && leftDamage > 0);
		return result;
	}
	
	static int[] readStats(Scanner in) {
		int attack = in.nextInt();
		int defend = in.nextInt();
		int health = in.nextInt();
		int leftDamage = in.nextInt();
		int rigthDamage = in.nextInt();
		return new int[]{attack, defend, health, leftDamage, rigthDamage};
	}
	
	static void fillTeam(Battle battle, int number, Scanner in) {

		System.out.printf("Введите количество существ в %s команде : \n", number==1 ? "первой" : "второй");
		int count = in.nextInt();
		for(int i = 0; i < count;i++) {
			System.out.println("Введите тип существа (игрок/монстр)");
			String type = in.next();
			int[] stats = readStats(in);
			while(!checkArgs(stats[0], stats[1], stats[2], stats[3], stats[4])) {
				System.out.println("Введите данные заново");
				stats = readStats(in);
			}
			Creature creature = (type == "игрок") ? 
					new Player(stats[0], stats[1], stats[2], stats[3], stats[4]) 
					: new Monster(stats[0], stats[1], stats[2], stats[3], stats[4]);
			if(number == 1) {
				battle.addToFirstTeam(creature);
			}
			else {
				battle.addToSecondTeam(creature);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Battle battle = new Battle();
		
		fillTeam(battle, 1, in);
		fillTeam(battle, 2, in);
		
		battle.startBattle();
	}
}
