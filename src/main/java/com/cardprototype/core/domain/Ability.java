package com.cardprototype.core.domain;

import org.thymeleaf.util.StringUtils;

/**
 * An ability a player can have
 * Only the id will be persisted in {@link Player}
 *
 * @author Kevin Deyne
 */
public class Ability {

	private String id;
	private AbilityType abilityType;

	private String name;

	private String type;
	private String description;

	private RangeType range;
	private int minDamage;
	private int maxDamage;
	private int lifespan;
	private int defenseBuff;
	private int accBuff;
	private int cooldown;

	public static final String COOLANT_FLUID = "skill-coolant";

	/**
	 * Sets up a <strong>Gauss mine drone</strong> ability<br/>
	 * EMP (no damage, target skips turn)<br/>
	 * DEF +5<br/>
	 * CLOSE Range<br/>
	 * ACC BASE 70<br/>
	 *
	 * @see {@link Ability}
	 */
	public static final String GAUSS_MINE_DRONE = "skill-emp";

	/**
	 * Sets up a <strong>Flammengranat Flak Cannon</strong> ability<br/>
	 * DMG 1-3 (lasts 3 turns)<br/>
	 * DEF -5<br/>
	 * ACC +5<br/>
	 * MID Range<br/>
	 * ACC BASE 90<br/>
	 * @see {@link Ability}
	 */
	public static final String FLAK_CANNON = "skill-flak";

	/**
	 * Sets up a <strong>Rotschild Gattling Gun</strong> ability<br/>
	 * DMG 1-10<br/>
	 * DEF +1 <br/>
	 * ACC -1<br/>
	 * MID Range<br/>
	 * ACC BASE 80<br/>
	 * @see {@link Ability}
	 */
	public static final String GATTLING_GUN = "skill-gattling";
	public static final String HARPOON = "skill-harpoon";
	public static final String OVERDRIVE = "skill-overdrive";

	public static final int BUFF_MINOR = 1;
	public static final int BUFF_STANDARD = 3;
	public static final int BUFF_MAJOR = 5;
	public static final int BUFF_LENGTH = 5;

	public Ability() {
		setLifespan(1);

		this.minDamage = 0;
		this.maxDamage = 0;

		this.defenseBuff = 0;
		this.accBuff = 0;
	}

	public AbilityType getAbilityType() {
		return this.abilityType;
	}

	public void setAbilityType(AbilityType abilityType) {
		this.abilityType = abilityType;
	}

	public String getRange() {
		return this.range.toString();
	}

	public void setRange(RangeType range) {
		this.range = range;
	}

	public String getType() {
		return "turret";
		//return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStats() {
		StringBuffer sBuffer = new StringBuffer(StringUtils.capitalize(getAbilityType().toString().toLowerCase().replace("_", " ")));

		if(getMaxDamage() != 0){
			sBuffer.append(", " + getMinDamage() + " - " + getMaxDamage() + " DMG");
		}

		if(getAccBuff() != 0) {
			sBuffer.append(", " + ((getAccBuff() > 0) ? "+" : "") + getAccBuff() + " ACC");
		}

		if(getDefenseBuff() != 0) {
			sBuffer.append(", " + ((getDefenseBuff() > 0) ? "+" : "") + getDefenseBuff() + " DEF");
		}

		return sBuffer.toString();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDefenseBuff() {
		return this.defenseBuff;
	}

	public void setDefenseBuff(int defenseBuff) {
		this.defenseBuff = defenseBuff;
	}

	public int getAccBuff() {
		return this.accBuff;
	}

	public void setAccBuff(int accBuff) {
		this.accBuff = accBuff;
	}

	public int getCooldown() {
		return this.cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getMinDamage() {
		return this.minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public int getMaxDamage() {
		return this.maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	public int getLifespan() {
		return this.lifespan;
	}

	public void setLifespan(int lifespan) {
		this.lifespan = lifespan;
	}
}