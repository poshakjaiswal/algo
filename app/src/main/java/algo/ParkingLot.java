package algo;

import java.util.Date;

public class ParkingLot {

    //Handle concurrent

    //
    //queye

//    A - B - C - D
//    E - F - G - H

    private ParkingLot myPrakingLot = null;


    public synchronized ParkingLot getInstance(){

    if (myPrakingLot != null) myPrakingLot = new ParkingLot();

    return myPrakingLot;

    }
}
