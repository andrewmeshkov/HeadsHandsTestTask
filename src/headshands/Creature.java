package headshands;

public class Creature {
	
	protected int attack, defend;
	protected int health;
	protected int leftDamage;
	protected int rigthDamage;
	private boolean death;
	
	public Creature(int attack, int defend, int health, int leftDamage, int rigthDamage) {
		if(1 <= attack && attack <= 30) {
			this.attack = attack;
		}
		if(1 <= defend && defend <= 30) {
			this.defend = defend;
		}
		if(health > 0) {
			this.setHealth(health);
		}
		if(rigthDamage > leftDamage && leftDamage > 0) {
			this.leftDamage = leftDamage;
			this.rigthDamage = rigthDamage;
		}
		this.death = false;
	}


	public void attack(Creature creature) {
		int count = Math.max(1, this.attack - creature.getDefend() + 1);
		boolean succesOfAttack  = false;
		for(int i = 0; i < count; i++) {
			succesOfAttack |= (Math.random() * 6) >= 4;
		}
		if(succesOfAttack) {
			creature.defend(this);
		}
		creature.checkDeath();
		
	}
	
	public void defend(Creature creature) {
		this.setHealth(Math.max(0, this.health - creature.getDamage()));
	}
	
	public void checkDeath() {
		if(this.getHealth() < 1) {
			this.setDeath(true);
		}
	}
	
	public void describe() {
		System.out.printf("Здоровье %d Урон %d Смерть %b \n" , this.health, this.attack, this.death);
	}
	
	// Getters and setters
	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefend() {
		return defend;
	}

	public void setDefend(int defend) {
		this.defend = defend;
	}
	public int getDamage() {
		return (int) ( this.leftDamage 
				+ (this.rigthDamage - this.leftDamage) * Math.random());
	}
	public boolean isDeath() {
		return this.death;
	}

	public void setLeftDamage(int leftDamage) {
		this.leftDamage = leftDamage;
	}

	public void setRigthDamage(int rigthDamage) {
		this.rigthDamage = rigthDamage;
	}

	public void setDeath(boolean death) {
		this.death = death;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
