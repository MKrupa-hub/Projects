package program;

import support.Mixer;

class MixerFrontBack extends Mixer {


    public MixerFrontBack(String name) {
        super(name);
    }

    @Override
    public void mix(int[] lightings) {
        int var;
        for (int i = 0; i < lightings.length/2; i++) {
            var=lightings[i];
            lightings[i]=lightings[lightings.length-i-1];
            lightings[lightings.length-i-1]=var;

        }

    }
}
