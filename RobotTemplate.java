/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import  edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVisionException;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot {
    private Joystick leftJoystick = new Joystick (2);
    private Joystick rightJoystick = new Joystick (1);
    private Joystick attackJS = new Joystick(3);
    private RobotDrive driveTrain = new RobotDrive(4,3,2,1);//1/2 left 3/4 right (real nums)
    private Shooter shtr= new Shooter(); 
    private Arm arm= new Arm();
    private ButtonLayout bL= new ButtonLayout(leftJoystick, rightJoystick, attackJS, shtr, arm);
    private Compressor comp = new Compressor(7, 6);// real numsb
    private Camera cam= new Camera();
    private Autonomous auto = new Autonomous(shtr,cam);
    /**
     * This function is called once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        while(isAutonomous()&& isEnabled()){
         
                try {
                    auto.auton();
                    //comp.start();
                    //shtr.setup();
                    //shtr.setup();
                } catch (NIVisionException ex) {
                    ex.printStackTrace();
                } catch (AxisCameraException ex) {
                    ex.printStackTrace();
                }
            }
            } 
            
        
    
    public void robotInit(){
        
        for(int i=0; i<2;i++){
        System.out.println("roboinit!!!");
        }
        comp.start();
        arm.up();
        shtr.setup();
    }
     /* public void disabled(){
      while(isDisabled()){
        robotInit();
        System.out.println("disabled");
      }
      } */     
    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl() {
        //arm.up();
        //.setup();
        // comp.start();
       //compressor is running
        double leftDriveVal = 0.0;
        double rightDriveVal = 0.0;
        while(isOperatorControl() && isEnabled()) {
            getWatchdog().feed();
            arm.up();
            shtr.setup();
            leftDriveVal = (leftJoystick.getY());
            rightDriveVal = (rightJoystick.getY()); 
            //driveTrain.tankDrive(leftDriveVal, rightDriveVal);
            driveTrain.tankDrive(EDC.convertVal(leftDriveVal), EDC.convertVal(rightDriveVal));
            //System.out.println(EDC.convertVal(leftDriveVal));
            //System.out.println(EDC.convertVal(rightDriveVal));
            bL.configureButtons();
            //Timer.delay(0.01);
           
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test() {
    
    }
}
