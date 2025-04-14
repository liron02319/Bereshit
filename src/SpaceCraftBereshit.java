
/**
 * This class implements the behavior and state of a lunar spacecraft.
 * It handles thrust, angle adjustments, fuel consumption, and motion updates.
 */


public class SpaceCraftBereshit implements ISpaceCraft {

    private static final double WEIGHT_EMP = 165; // Empty weight of spacecraft (kg)

    // State variables
    private double vs;     // Vertical speed (m/s)
    private double hs;     // Horizontal speed (m/s)
    private double ang;    // Angle of spacecraft (degrees)
    private double fuel;   // Remaining fuel (kg)
    private double NN;     // Thrust level [0,1]
    private double dist;   // Horizontal distance to landing site (m)
    private double alt;    // Altitude (m)
    private double time;   // Elapsed time (s)
    private double dt;     // Time step for simulation (s)
    private double acc;    // Current acceleration (m/s^2)
    private double weight; // Total weight (kg)

    /**
     * Full constructor for initializing the spacecraft's state.
     */
    public SpaceCraftBereshit(double vs, double hs, double ang, double fuel, double NN,
                      double dist, double alt, double time, double dt, double acc, double weight) {
        this.vs = vs;
        this.hs = hs;
        this.ang = ang;
        this.fuel = fuel;
        this.NN = NN;
        this.dist = dist;
        this.alt = alt;
        this.time = time;
        this.dt = dt;
        this.acc = acc;
        this.weight = weight;
    }


    /**
     * Calculates the spacecraft's acceleration using thrust from main and secondary engines.
     */
    @Override
    public double acc(double weight, boolean main, int seconds) {
        double t = 0;
        if (main) {
            t += Bereshit_101.MAIN_ENG_F;
        }
        t += seconds * Bereshit_101.SECOND_ENG_F;
        return t / weight;
    }

    /**
     * Computes the next physical state of the spacecraft using physics equations.
     */
    @Override
    public void compute_next_step() {
        double ang_rad = Math.toRadians(ang);
        double h_acc = Math.sin(ang_rad) * acc;
        double v_acc = Math.cos(ang_rad) * acc;
        double vacc = Moon.getAcc(hs);

        time += dt;

        double dw = dt * Bereshit_101.ALL_BURN * NN;
        if (fuel > 0) {
            fuel -= dw;
            weight = WEIGHT_EMP + fuel;
            acc = NN * acc_max(weight);
        } else {
            acc = 0; // No fuel, no acceleration
        }

        v_acc -= vacc;

        if (hs > 0) {
            hs -= h_acc * dt;
        }

        dist -= hs * dt;
        vs -= v_acc * dt;
        alt -= dt * vs;

        if (hs < 2.5) {
            hs = 0;
        }
    }



    /**
     * Increases thrust level, clamped between 0 and 1.
     */
    @Override
    public void increase_thrust(double inc) {
        double val = this.NN + inc;
        if (val >= 0 && val <= 1) {
            this.NN = val;
        }
        if (val > 1) {
            this.NN = 1;
        }
        if (val < 0) {
            this.NN = 0;
        }
    }

    /**
     * Increases the spacecraft's angle, clamped between 0 and 90 degrees.
     */
    @Override
    public void increase_angle(double angInc) {
        double val = this.ang + angInc;
        if (val >= 0 && val <= 90) {
            this.ang = val;
        }
        if (val > 90) {
            this.ang = 90;
        }
        if (val < 0) {
            this.ang = 0;
        }
    }

    /**
     * Calculates maximum possible acceleration based on current weight.
     */
    @Override
    public double acc_max(double weight) {
        return acc(weight, true, 8);
    }

    // Getters and setters with documentation:

    /** @return Vertical speed (m/s) */
    @Override
    public double getVs() { return vs; }

    /** @param vs Vertical speed (m/s) */
    @Override
    public void setVs(double vs) { this.vs = vs; }

    /** @return Horizontal speed (m/s) */
    @Override
    public double getHs() { return hs; }

    /** @param hs Horizontal speed (m/s) */
    @Override
    public void setHs(double hs) { this.hs = hs; }

    /** @return Current angle (degrees) */
    @Override
    public double getAng() { return ang; }

    /** @param ang New angle (degrees) */
    @Override
    public void setAng(double ang) { this.ang = ang; }

    /** @return Remaining fuel (kg) */
    @Override
    public double getFuel() { return fuel; }

    /** @param fuel New fuel amount (kg) */
    @Override
    public void setFuel(double fuel) { this.fuel = fuel; }

    /** @return Thrust power [0,1] */
    @Override
    public double getNN() { return NN; }

    /** @param NN New thrust power [0,1] */
    @Override
    public void setNN(double NN) { this.NN = NN; }

    /** @return Horizontal distance to landing (m) */
    @Override
    public double getDist() { return dist; }

    /** @return Altitude (m) */
    @Override
    public double getAlt() { return alt; }

    /** @return Elapsed time (s) */
    @Override
    public double getTime() { return time; }

    /** @return Simulation step size (s) */
    @Override
    public double getDt() { return dt; }

    /** @return Current acceleration (m/s^2) */
    @Override
    public double getAcc() { return acc; }

    /** @return Current total weight (kg) */
    @Override
    public double getWeight() { return weight; }

}