/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
 
import edu.wpi.first.wpilibj.Joystick;
/**
 *
 * @author anna
 */
public class ButtonLayout{
    private Joystick leftStick;
    private Joystick rightStick;
    private Joystick attackController;
    private Shooter shooter;
    private Arm ballGetter;
     //the ballgetter is the arm, and is used to roll the ball into the robot for shooting

    
    public ButtonLayout(Joystick left, Joystick right, Joystick atk,
                             Shooter shoot, Arm ballGetter){
        leftStick= left;
        rightStick= right;
        attackController= atk;
        shooter= shoot;
        this.ballGetter= ballGetter;
    }
    
    public void configureButtons() {
        
        if (attackController.getRawButton(1)){
            shooter.shoot();
        //when button 1 on the attack controller is pressed, the shooter shoots (see shooter code)

        }
        if (attackController.getRawButton(2)){
            ballGetter.down();
            //when button 2 on the attack controller is pressed, the arm drops down on the ball
        }

        
        if (attackController.getRawButton(4)){
            ballGetter.up();
            //when button 4 on the attack controller is pressed, the arm is raised

        }
        if (attackController.getRawButton(6)){
            ballGetter.spin();
            //when button 6 on the attack controller is pressed, the roller on the arm will spin, rolling the ball into to the robot and preparing it to be shot

        }
        else if(!attackController.getRawButton(6)){
            ballGetter.spinStop();
            //when button 6 is not being pressed on the attack controller, the roller on the arm will not move
        }
        
    }
}
