/**
 * The PID class implements a Proportional-Integral-Derivative controller.
 * This type of controller is widely used in control systems to calculate an output
 * value based on the current error, its accumulation over time (integral), and its rate of change (derivative).
 *
 * Fields:
 * - P: Proportional gain
 * - I: Integral gain
 * - D: Derivative gain
 * - last_error: Stores the error from the previous update to compute the derivative term
 * - first_run: Boolean flag to handle initialization of last_error
 * - integral_error: Accumulates the integral of the error over time
 *

 */

public class PID {

    private double P;
    private double I;
    private double D;
    private double last_error;
    private boolean first_run;
    private double integral_error;


    /**
     * Constructor:
     * - PID(double p, double i, double d): Initializes the controller with specified gains
     *
     */
    public PID(double p, double i, double d) {
        this.P = p;
        this.I = i;
        this.D = d;
        this.first_run = true;
        this.integral_error = 0;
    }



    /**
     * Methods:
     * update(double error, double dt): Computes the control output based on the current error and elapsed time
     *
     */
    public double update(double error, double dt) {
        if (this.first_run) {
            this.first_run = false;
            this.last_error = error;
        }
        double diff = (error - this.last_error) / dt;
        integral_error += error * dt;
        double control_out = P * error + I * integral_error + D * diff;
        this.last_error = error;
        return control_out;
    }


}
