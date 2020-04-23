package EmF;

import robocode.*;

public class EmFBot extends Robot {
	boolean movingForward;
	
	public void run() {
		turnLeft(getHeading() % 90);
		turnGunRight(90);
		while (true) {
			ahead(120);
		}
	}
	
	public void onHitWall(HitWallEvent e) {
		// Bounce off!
		reverseDirection();
	}
	
	public void reverseDirection() {
		if (movingForward) {
			ahead(40000);
			movingForward = false;
		} else {
			ahead(40000);
			movingForward = true;
		}
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
	}
}
