/**
 * Interface representing a generic spacecraft and its control capabilities.
 */
public interface ISpaceCraft {


    /**
     * Calculates the maximum acceleration based on the current weight.
     * @param weight the current weight of the spacecraft
     * @return the maximum possible acceleration
     */
    double acc_max(double weight);

    /**
     * Calculates the acceleration based on engine status.
     * @param weight current spacecraft weight
     * @param main whether main engine is on
     * @param seconds number of seconds for secondary engines
     * @return the resulting acceleration
     */
    double acc(double weight, boolean main, int seconds);

    /**
     * Computes and updates the spacecraft's next physical state.
     */
    void compute_next_step();



    /**
     * Increase the engine thrust within bounds [0,1].
     * @param inc the amount to increase
     */
    void increase_thrust(double inc);

    /**
     * Increase the spacecraft's angle within bounds [0,90] degrees.
     * @param angInc the angle increment
     */
    void increase_angle(double angInc);

    // Getters and setters for spacecraft state variables:

    double getVs();
    void setVs(double vs);

    double getHs();
    void setHs(double hs);

    double getAng();
    void setAng(double ang);

    double getFuel();
    void setFuel(double fuel);

    double getNN();
    void setNN(double NN);

    double getDist();
    double getAlt();
    double getTime();
    double getDt();
    double getAcc();
    double getWeight();
}