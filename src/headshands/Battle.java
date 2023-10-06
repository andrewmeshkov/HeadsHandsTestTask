package headshands;

import java.util.ArrayList;

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
