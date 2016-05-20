/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 

//this is the logarithm/exponential driver code

package edu.wpi.first.wpilibj.templates;
import com.sun.squawk.util.MathUtils;
/**
 *
 * @author student
 */
public class EDC{
    private static final double base= 1.047128558; 
    public static double convertVal(double jVal){
    double value;
    if(jVal>0){
        value= MathUtils.pow(base,(jVal*100))/100;
    }
    else if(jVal==0){
        value=0.0;
    }
    else{
    value= -(MathUtils.pow(base,(jVal*-100))/100);
    }
    return value;
    }
}
