package headshands;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Дополнительный класс Battle, которые позволяет провести бой между двумя командами
 * Мы случайно выбираем одного персонажа из одной команды и он должен нанести удар по 
 * другому также случайно выбранному персонажу из команды противника
 * так продолжается до тех пор пока все персонажи одной из команд не будут мертвы
*/
public class Battle {
	
	protected ArrayList<Creature> firstTeam;
	protected ArrayList<Creature> secondTeam;
	protected boolean started;
	
	public Battle() {
		this.started = false;
		this.firstTeam = new ArrayList<>();
		this.secondTeam = new ArrayList<>();
		this.fillTeam(1, new Scanner(System.in));
		this.fillTeam(2, new Scanner(System.in));
	}
	
	void addToFirstTeam(Creature c) {
		this.firstTeam.add(c);
	}
	
	void addToSecondTeam(Creature c) {
		this.secondTeam.add(c);
	}
	
	private void teamAttack(ArrayList<Creature> attackers, ArrayList<Creature> defenders) {
		int index_attack = (int) Math.floor(Math.random() * attackers.size());
		int index_defender = (int) Math.floor(Math.random() * defenders.size());
		attackers.get(index_attack).attack(defenders.get(index_defender));
		if(defenders.get(index_defender).isDeath()) {
			defenders.remove(index_defender);
		}
	}
	
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
	
	 void fillTeam(int number, Scanner in) {

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
				this.addToFirstTeam(creature);
			}
			else {
				this.addToSecondTeam(creature);
			}
		}
	}
	
	void startBattle() {
		this.started = true;
		while(!this.firstTeam.isEmpty() && !this.secondTeam.isEmpty()) {
			teamAttack(this.firstTeam,this.secondTeam);
			if(!this.secondTeam.isEmpty()) {
				teamAttack(this.secondTeam, this.firstTeam);
			}
		}
		if(this.firstTeam.isEmpty()) {
			System.out.println("Выиграла вторая команда");
		}
		else {
			System.out.println("Выиграла первая команда");
		}
		
	}
}
