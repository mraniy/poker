package com.ymcraftservices.features;

import com.ymcraftservices.model.Player;

public class CommonDataBetweenSteps {

    protected static Player aria = new Player("Stark","Aria",null, null);

    protected static Player littleFinger = new Player("Finger","Little",null, null);

    protected static Player boltonRamsey =  new Player("Ramsey","Bolton",null, null);





    public static Player getAria() {
        return aria;
    }

    public static Player getLittleFinger() {
        return littleFinger;
    }

    public static Player getBoltonRamsey() {
        return boltonRamsey;
    }

}
