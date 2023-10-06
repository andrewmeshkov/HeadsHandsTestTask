package headshands;


public class Player extends Creature{
	protected int heal, maxHealth;
	public Player(int attack, int defend, int health, int left_damage, int rigth_damage) {
		super(attack, defend, health, left_damage, rigth_damage);
		this.heal = 4;
		this.maxHealth = health;
	}
	@Override
	public void attack(Creature creature) {
		super.attack(creature);
	}
	@Override
	public void defend(Creature creature) {
		super.defend(creature);
		if(this.health < (int) 0.3 * this.maxHealth && this.heal > 0) {
			this.health += (int) 0.3 * this.maxHealth;
			this.heal -= 1;
		}
	}
	public int getHeal() {
		return heal;
	}
	public void setHeal(int heal) {
		this.heal = heal;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
		

}
