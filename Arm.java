/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
  
import edu.wpi.first.wpilibj.Talon;
/**
 *
 * @author anna
 */
public class Arm{
    private Piston armPiston;
    private Piston armPiston2;
    private Talon roller;
    public Arm(){
        armPiston= new Piston(9);
        armPiston2= new Piston(7);
        roller= new Talon(5);//real num
    }
    public void down(){
        armPiston.retract();
        armPiston2.retract();
        //when this method “down” is being called, both pistons are retracted, making the arm go down. At this point, the arm will be over the ball.

    }
    public void up(){
        armPiston.extend();
        armPiston2.extend();
        //when this method “up” is being called, both pistons are extended which lifts the arm up, off of the ball.
    }
    public void spin(){
        roller.set(1.0);
        //makes the roller spin by setting the speed to 1.0 (forwards). The roller is used to roll the ball into the robot, prepping it to be shot into the goal
    }
    public void spinStop(){
        roller.set(0.0);
        //stops the roller by setting the roller speed back to 0. After the ball is in place on the robot, the roller can stop so the ball can be shot.

    }
}
