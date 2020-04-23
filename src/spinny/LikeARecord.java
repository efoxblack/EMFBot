package spinny;

import robocode.RateControlRobot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class LikeARecord extends RateControlRobot {
	
	public void run() {
		setBodyColor(Color.darkGray);
		setGunColor(Color.black);
		setRadarColor(Color.lightGray);
		setScanColor(Color.white);
		setBulletColor(Color.green);
		
		while(true ) {
			setTurnRight(1000);
			
			setVelocityRate(5);
			
			ahead(5000);
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(5);
	}
	
	public void onHitRobot(HitRobotEvent e) {
		if (e.getBearing() > -10 && e.getBearing() < 10) {
			fire(5);
		}
		if (e.isMyFault()) {
			turnRight(10);
		}
	}
	
	public void onHitByBullet(HitByBulletEvent e) {
		setVelocityRate(-5);
	}
	
	public void onHitWall(HitWallEvent e) {
		setVelocityRate(-1 * getVelocityRate());
	}

}
