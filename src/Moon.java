//package simulation;
/**
 * The Moon class provides basic physical constants and a method for calculating
 * a simplified model of lunar gravitational acceleration based on horizontal speed.
 * The data is based on information from the Hebrew Wikipedia article on the Moon.
 *
 * Constants:
 * - RADIUS: Radius of the Moon in meters (3475 km)
 * - ACC: Surface gravitational acceleration on the Moon in m/s²
 * - EQ_SPEED: Approximate horizontal speed at which gravitational acceleration is reduced to zero
 *
 * Methods:
 * - getAcc(double speed): Returns a reduced gravitational acceleration based on the input speed,
 *   using a simple linear model where acceleration decreases as speed approaches EQ_SPEED.
 */


public class Moon {
	// from: https://he.wikipedia.org/wiki/%D7%94%D7%99%D7%A8%D7%97
	public static final double RADIUS = 3475*1000; // meters
	public static final double ACC = 1.622;// m/s^2
	public static final double EQ_SPEED = 1700;// m/s
	
	public static double getAcc(double speed) {
		double n = Math.abs(speed)/EQ_SPEED;
		double ans = (1-n)*ACC;
		return ans;
	}
}
