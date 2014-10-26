package com.engine;

import java.io.Serializable;
import java.lang.CloneNotSupportedException;

import com.engine.constants.Gravity;
import com.engine.constants.PlanetaryMass;

public class Engine implements Serializable {

	private static final long serialVersionUID = 3657152894229143618L;
	private static Engine engine;
	public final PlanetaryMass masses = new PlanetaryMass();
	public final Gravity gravity = new Gravity();
	
	private Engine() {
	}
	
	public static synchronized Engine getEngine() {
		if(engine == null) {
			engine = new Engine();
		}
		return engine;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
		
	}
	
	public static void main(String[] args) {
		final Engine engine = Engine.getEngine();
		System.out.println(engine.masses.EARTH_MASS);
		System.out.println(engine.gravity.EARTH_GRAVITY);
	}

}
