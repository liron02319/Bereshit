//package Drone;

//import simulation.Moon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class represents the basic flight controller of the Bereshit space craft.
 * @author ben-moshe
 * liron cohen
 *
 */
public class Bereshit_101 {
	public static final double WEIGHT_EMP = 165; // kg
	public static final double WEIGHT_FULE = 420; // kg
	public static final double WEIGHT_FULL = WEIGHT_EMP + WEIGHT_FULE; // kg
// https://davidson.weizmann.ac.il/online/askexpert/%D7%90%D7%99%D7%9A-%D7%9E%D7%98%D7%99%D7%A1%D7%99%D7%9D-%D7%97%D7%9C%D7%9C%D7%99%D7%AA-%D7%9C%D7%99%D7%A8%D7%97
	public static final double MAIN_ENG_F = 430; // N
	public static final double SECOND_ENG_F = 25; // N
	public static final double MAIN_BURN = 0.15; //liter per sec, 12 liter per m'
	public static final double SECOND_BURN = 0.009; //liter per sec 0.6 liter per m'
	public static final double ALL_BURN = MAIN_BURN + 8*SECOND_BURN;


	/*
	public static double accMax(double weight) {
		return acc(weight, true,8);
	}
	*/

	/*
	public static double acc(double weight, boolean main, int seconds) {
		double t = 0;
		if(main) {t += MAIN_ENG_F;}
		t += seconds*SECOND_ENG_F;
		double ans = t/weight;
		return ans;
	}*/
	// 14095, 955.5, 24.8, 2.0



	public static void main(String[] args) {
		System.out.println("Simulating Bereshit's Landing:");
		// starting point:
		double vs = 24.8;
		double hs = 932;
		double dist = 181*1000;
		double ang = 58.3; // zero is vertical (as in landing)
		double alt = 13748; // 2:25:40 (as in the simulation) // https://www.youtube.com/watch?v=JJ0VfRL9AMs
		double time = 0;
		double dt = 1; // sec
		double acc=0; // Acceleration rate (m/s^2)
		double fuel = 121; // 
		double weight = WEIGHT_EMP + fuel;
		double NN = 0.7; // rate[0,1]

		// Prepare for CSV writing
		String filePath = "simulation_results.csv";
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			// Write headers to CSV
			writer.write("time, alt, vs, desired_vs, hs, desired_hs, ang, desired_ang, acc, nn, fuel");
			writer.newLine();



		// create a spacecraft with the starting conditions
			SpaceCraftBereshit craft = new SpaceCraftBereshit(vs, hs, ang, fuel, NN, dist, alt, time, dt, acc, weight);

		PID pid = new PID(0.014, 0.000000003, 0.2);
		PID pid_ang = new PID(0.314, 0.00003, 0.13);

			//TITLE - TO SEE IN RUNNING FOR ME CHECK
			System.out.println("time, alt, vs, desired_vs, hs, desired_hs, ang, desired_ang, acc, nn, fuel");

		// ***** main simulation loop ******
		while (craft.getAlt() > 0) {

			// get the desired vs, hs, angle
			double dsAngle = get_desired_angle(craft.getAlt());
			double desired_vs = get_desired_vs(craft.getAlt());
			double desired_hs = get_desired_hs(craft.getAlt());

			if (time % 10 == 0 || craft.getAlt() < 100) {
				//TO SEE IN RUNNING FOR ME CHECK
				System.out.println(time + ", " +craft.getAlt() + ", " + craft.getVs() + ", " + desired_vs + ", " + craft.getHs() + ", " + desired_hs + ", " + craft.getAng() + " , " + dsAngle + ", " + craft.getAcc() + ", " + craft.getNN() + ", " + craft.getFuel());

				//DATA FOR CSV
				String dataLine = time + ", " + craft.getAlt() + ", " + craft.getVs() + ", " + desired_vs + ", "
						+ craft.getHs() + ", " + desired_hs + ", " + craft.getAng() + ", " + dsAngle
						+ ", " + craft.getAcc() + ", " + craft.getNN() + ", " + craft.getFuel();
				writer.write(dataLine);
				writer.newLine();
			}

			// this pid takes the sum of horizontal and vertical errors
			double thrustIncr = pid.update(craft.getVs() - desired_vs + craft.getHs() - desired_hs, craft.getDt());

			// angel correction
			double angIncr = pid_ang.update(dsAngle - craft.getAng(), craft.getDt());

			// adjust angle
			craft.increase_angle(angIncr);

			// adjust thrust
			craft.increase_thrust(thrustIncr);

			// physics stuff
			craft.compute_next_step();

			// increment time by dt
			time += dt;  // Add this line to update time
		}

		//At the very end of the simulation (when the lander reaches the ground):
	//	System.out.println(craft.getAlt() + ", " + craft.getVs() + ", " + craft.getHs() + ", " + craft.getAng() + ", " + craft.getAcc() + ", " + craft.getNN() + ", " + craft.getFuel());


			System.out.println("Simulation data has been written to " + filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//HELp FUNCTIONS

	/**
	 * Calculates the desired horizontal speed based on the spacecraft's altitude.
	 * For altitudes below 2000 meters, the target is to have no horizontal speed.
	 * For altitudes above 30000 meters, the target is to match the Moon's rotational speed.
	 * Between 2000 and 30000 meters, the speed is linearly interpolated.
	 *
	 *  Purpose:
	 * To calculate the ideal horizontal speed the spacecraft should have, depending on how high it is (its altitude).
	 *
	 *  Explanation:
	 * If you're very low (under 2000 meters): You shouldn't move sideways at all — horizontal speed = 0.
	 *
	 * If you're very high (above 30,000 meters): You want to match the Moon's rotation, so use full horizontal speed (Moon.EQ_SPEED).
	 *
	 * If you're in between: It calculates a proportional speed — gradually decreasing the speed from full to zero as you descend from 30,000m to 2000m.
	 *
	 *  Why it's needed:
	 * To make sure the spacecraft gradually slows down horizontally as it descends — this helps ensure a safe, vertical landing.
	 *
	 * @param alt current altitude in meters
	 * @return the desired horizontal speed in m/s
	 */
	private static double get_desired_hs(double alt) {
		double minAlt = 2000;
		double maxAlt = 30000;
		if (alt < minAlt)
			return 0;
		if (alt > maxAlt)
			return Moon.EQ_SPEED;

		double norm = (alt - minAlt) / (maxAlt - minAlt);
		return norm * Moon.EQ_SPEED;
	}

	/**
	 * Calculates the desired vertical speed based on the spacecraft's altitude.
	 * Higher altitudes allow faster descent, while lower altitudes require slower speeds
	 * to ensure a soft landing.
	 *
	 *
	 *  Purpose:
	 * To decide how fast the spacecraft should fall vertically, based on how close it is to the surface.
	 *
	 * Explanation:
	 * Above 8000m: Fall quickly (30 m/s).
	 *
	 * Between 8000m and 500m: Gradually slower (24 → 12 → 6 → 3 → 2 → 1).
	 *
	 * Very close to the surface: Move very slowly (1 m/s) for a soft landing.
	 *
	 * ➤ Why it's needed:
	 * To avoid crashing! You want to fall faster when high, but slow down gently as you approach the ground.
	 *
	 * @param alt current altitude in meters
	 * @return the desired vertical speed in m/s
	 */
	private static double get_desired_vs(double alt) {
		if (alt > 8000)
			return 30;

		if (alt > 500)
			return 24;

		if (alt > 300)
			return 12;

		if (alt > 100)
			return 6;

		if (alt > 50)
			return 3;

		if (alt > 25)
			return 2;

		return 1;
	}

	/**
	 * Calculates the desired engine angle based on the spacecraft's altitude.
	 * Higher altitudes require greater tilt to reduce horizontal speed. Near the surface,
	 * the engine should point straight down for a vertical landing.
	 *
	 *
	 *  Purpose:
	 * To determine how much the spacecraft should tilt (angle) — which affects how much thrust is used for horizontal vs vertical movement.
	 *
	 *  Explanation:
	 * Above 1500m: Tilt at 60° — helps reduce horizontal speed.
	 *
	 * As you get lower: Tilt less (50°, 30°...).
	 *
	 * Below 1000m: Tilt = 0° — point straight down to focus on vertical landing.
	 *
	 *  Why it's needed:
	 * A higher angle helps reduce sideways movement. Once horizontal speed is low, point down and descend safely.
	 *
	 * @param alt current altitude in meters
	 * @return the desired engine angle in degrees (0 to 60)
	 */
	private static double get_desired_angle(double alt) {
		if (alt > 1500)
			return 60;
		if (alt > 1200)
			return 50;
		if (alt > 1000)
			return 30;
		return 0;
	}

}
