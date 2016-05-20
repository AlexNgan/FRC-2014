/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
 
import edu.wpi.first.wpilibj.Solenoid;
/**
 *
 * @author student
 */
public class Piston{
    
    private Solenoid piston;
    
    public Piston (int port){
        //port will be replaced by the correct # for the robot being used
        piston= new Solenoid (port);
    }
    
    public void extend(){
        piston.set(true);
    }
    
    public void retract(){
        piston.set(false);
    }  
}
