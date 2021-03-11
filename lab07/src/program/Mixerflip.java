package program;

import support.Mixer;

class Mixerflip extends Mixer {

    public Mixerflip(String name) {
        super(name);
    }

    @Override
    public void mix(int[] lightings) {
        int var;
        for (int i = 0; i < lightings.length; i=i+2) {
            var=lightings[i];
            lightings[i]=lightings[i+1];
            lightings[i+1]=var;

        }

    }
}



