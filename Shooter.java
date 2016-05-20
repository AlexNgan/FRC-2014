/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.Timer;
/**
 *
 * @author student
 */
public class Shooter {
    private  Piston shotGate;
    private Piston shooterPiston;
    
    public Shooter(){
    shotGate= new Piston(3);
    shooterPiston = new Piston(1);
    }
    
    public void shoot(){
        shotGate.retract();
        System.out.println("SHOoTER");
        Timer.delay(0.1);
        shooterPiston.retract();
        Timer.delay(0.1);
        shotGate.extend();
        shooterPiston.extend();
    }
    public void setup(){
        shotGate.extend();
        shooterPiston.extend();
    }
}
