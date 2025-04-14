

/*

public class oldSimulation {
    // ***** main simulation loop ******
		while(alt>0) {
        if(time%10==0 || alt<100) {
            System.out.println(time+","+vs+","+hs+","+dist+","+alt+","+ang+","+weight+","+acc);
        }
        // over 2 km above the ground
        if(alt>2000) {	// maintain a vertical speed of [20-25] m/s
            if(vs >25) {NN+=0.003*dt;} // more power for braking
            if(vs <20) {NN-=0.003*dt;} // less power for braking
        }
        // lower than 2 km - horizontal speed should be close to zero
        else {
            if(ang>3) {ang-=3;} // rotate to vertical position.
            else {ang =0;}
            NN=0.5; // brake slowly, a proper PID controller here is needed!
            if(hs<2) {hs=0;}
            if(alt<125) { // very close to the ground!
                NN=1; // maximum braking!
                if(vs<5) {NN=0.7;} // if it is slow enough - go easy on the brakes
            }
        }
        if(alt<5) { // no need to stop
            NN=0.4;
        }
        // main computations
        double ang_rad = Math.toRadians(ang);
        double h_acc = Math.sin(ang_rad)*acc;
        double v_acc = Math.cos(ang_rad)*acc;
        double vacc = Moon.getAcc(hs);
        time+=dt;
        double dw = dt*ALL_BURN*NN;
        if(fuel>0) {
            fuel -= dw;
            weight = WEIGHT_EMP + fuel;
            acc = NN* accMax(weight);
        }
        else { // ran out of fuel
            acc=0;
        }

        v_acc -= vacc;
        if(hs>0) {hs -= h_acc*dt;}
        dist -= hs*dt;
        vs -= v_acc*dt;
        alt -= dt*vs;
    }
}

*/
