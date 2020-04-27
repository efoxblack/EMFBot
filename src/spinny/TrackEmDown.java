package spinny;

import robocode.*;
import robocode.util.Utils;
import java.awt.*;



public class TrackEmDown extends AdvancedRobot {
	
	int moveDirection = 1; //Describes which direction we are moving
	
	public void run() {
		setBodyColor(Color.magenta);
		setGunColor(Color.black);
		setRadarColor(Color.lightGray);
		setScanColor(Color.white);
		setBulletColor(Color.yellow);
		
		setAdjustGunForRobotTurn(true); //Keep the gun from moving while we turn
		setAdjustRadarForRobotTurn(true); //Keep the radar from moving while we turn
		turnRadarRightRadians(Double.POSITIVE_INFINITY); //Keep the radar turning right
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		double absBearing = e.getBearingRadians() + getHeadingRadians(); //The absolute location bearing of the enemy
		
		double latVel = e.getVelocity() * Math.sin(e.getHeadingRadians() - absBearing); //The later velocity of the enemy
		
		double gunTurnAmt; //Declare how much to turn the gun
		
		setTurnRadarLeftRadians(getRadarTurnRemainingRadians()); //Lock the radar onto the enemy
		
		if (Math.random() > 0.9) { //If the math.random is greater than 0.9
			setMaxVelocity((12 * Math.random()) + 12); //then randomly change speed
		}
		
		if (e.getDistance() > 150) { //If the enemy is more than 150 units away
			gunTurnAmt = Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / 18); //How much our gun is going to turn, with a bit of lead
			setTurnGunRightRadians(gunTurnAmt); //Set our gun turn
			setTurnRightRadians(Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / getVelocity())); //Set the turn for the enemies predicted location
			setAhead((e.getDistance() - 100) * moveDirection); //Actually move
			setFire(3); //Fire, hard
		} else { //If we are closer than 150 units
			gunTurnAmt = Utils.normalRelativeAngle(absBearing - getGunHeadingRadians() + latVel / 8); //How much our gun is going to turn, with a bit of lead
			setTurnGunRightRadians(gunTurnAmt); //Set our gun turn
			setTurnLeft(-90 - e.getBearing()); //Turn so we are perpendicular to the enemy
			setAhead((e.getDistance() - 100) * moveDirection); //Actually move
			setFire(3); //Fire, hard
		}
		
	}
	
	public void onHitWall(HitWallEvent e) {
		moveDirection = -moveDirection; //If we hit a wall, reverse direction
	}
}
