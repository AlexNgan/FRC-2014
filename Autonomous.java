package edu.wpi.first.wpilibj.templates;

//import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;

public class Autonomous {
   

Shooter shooter ;
Camera camera ;

public Autonomous(Shooter shtr, Camera cam){
    shooter=shtr;
    camera=cam;
}
public void auton() throws NIVisionException, AxisCameraException {
    try {
        if (camera.hotGoalsPresent()) {
            for(int i=0;i<15;i++){
            System.out.println ("Goal Detected.");  }   // Prints out "Goal detected" if boolean is true
        }
        
        else if (!camera.hotGoalsPresent()){
            for(int i=0;i<15;i++){
            System.out.println ("No Goals Detected!");  }  // Prints out "No goals detected if boolean is false
        }
    } catch (NIVisionException ex) {
        ex.printStackTrace();
    }
    catch (AxisCameraException ex) {
        ex.printStackTrace();
    }
   }   
}
