package support;

import java.io.Serializable;

public abstract class Mixer implements Serializable {

	private String name = "";


	public abstract void mix(int[] lightings);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name=name;
	}


	public Mixer(String name) {
		this.name = name;
	}

}
