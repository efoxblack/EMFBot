package emF;

import robocode.HitByBulletEvent;
import robocode.HitWallEvent;
import robocode.RateControlRobot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class EmFBot_v2 extends RateControlRobot {
	
	int turnCounter;
	
	public void run() {
		setBodyColor(Color.red);
		setGunColor(Color.red);
		setRadarColor(Color.orange);
		setScanColor(Color.yellow);
		setBulletColor(Color.red);
		
		turnCounter = 0;
		setGunRotationRate(8);
		
		while (true) {
			if (turnCounter % 64 == 0) {
				setTurnRate(0);
				
				setVelocityRate(6);
			}
			if (turnCounter % 64 == 32) {
				setVelocityRate(-8);
			}
			turnCounter++;
			execute();
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		setTurnRate(3);
	}
	
	public void onHitWall(HitWallEvent e) {
		setVelocityRate(-1 * getVelocityRate());
	}
}
