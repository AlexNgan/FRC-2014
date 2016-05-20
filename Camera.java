package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
//import edu.wpi.first.wpilibj.camera.AxisCameraException;
//import edu.wpi.first.wpilibj.image.HSLImage;
//import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
//import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.Image;



/**
*
* @authors ngang/suazor/wicksk
*/

public class Camera {

    CriteriaCollection filterGroup;
    //CriteriaCollection is a group of conditions for the particle filter to use.
    public AxisCamera hotGoalDetect;
         
  
    public void imageProcesser() {
         /*imageProcesser instantiates the CriteriaCollection/Camera 
         and looks for rectangles of a certain height and width*/
         filterGroup = new CriteriaCollection();
         filterGroup.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
         filterGroup.addCriteria(NIVision.MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
         filterGroup.addCriteria(NIVision.MeasurementType.IMAQ_MT_AREA, 150, 65535, false);
          /*MIN AND MAX PIXEL LIMITS NEED TO BE TESTED AND ADJUSTED THESE ARE JUST PLACEHOLDER VALUES FOR THE LEGIT ONES. 
         CAN ONLY BE TESTED ONCE WE CAN TEST CODE. AKA CONNECT TO ROBOT.*/
         hotGoalDetect = AxisCamera.getInstance(); 
       
}
   public boolean hotGoalsPresent() throws NIVisionException, AxisCameraException {
        boolean goalsThere = false;
       /*Creates the boolean which is returned at the end of the method which
       says if the hot goals are present.*/
  
    
       try {
           ColorImage currentPic = hotGoalDetect.getImage(); 
          // ColorImage currentPic;
           //currentPic = new RGBImage ("/HotGoal.jpg");
           
           /*a sample image taken with the axis camera must be inserted for 
             testing -  getImage uses realtime pictures from the camera */
           
           BinaryImage colorIsolation = currentPic.thresholdHSL(75, 200, 70, 100, 10, 65);
           /*This only keeps green items (such as the retroreflective tape 
           will be if a green led is shined onto it.)*/
         //  BinaryImage bigParticlesOnly = colorIsolation.removeSmallObjects(false, 3);
           /*Keep only particles above a certain size by eroding the image 
           down three times. The 'false' here indicates that it is
           connectivity-4. Connectivity-4 means that particles are 
           only considered connected to each other if they touch directly above,
           below, left, and right (imagine a tic-tac-toe board: the particles 
           are only considered connected to the center top, center bottom, 
           middle left, and middle  right.) Connectivity-8 would mean that a 
           particle in the center of the board would be connected to every
           other square in the tic-tac-toe board.*/
           BinaryImage fillRectangle = colorIsolation.convexHull(false);
           //Fills in rectangles found even if they are slightly covered up.
           BinaryImage findTape = fillRectangle.particleFilter(filterGroup);
           /*Finds the filled in rectangles which match the criteria in the
           CriteriaCollection.*/
           int particleNumber = findTape.getNumberParticles();
          
           
            for (int i = 1; i < findTape.getNumberParticles(); i++) {
                    ParticleAnalysisReport ParticleReport = findTape.getParticleAnalysisReport(i);
          //Gets the number of eliglible goals (particles)
            }
          currentPic.free();
          colorIsolation.free();
        //  bigParticlesOnly.free();
          fillRectangle.free();
          findTape.free();
          
          
          if (particleNumber > 1) {
              goalsThere = true;
              System.out.println("More Than One Particle Found.");
           }
          //If there are eligible goals in the area, sets bool to true.
          else if (particleNumber == 1){
              goalsThere = false;
              for(int i=0;i<10;i++){
              System.out.println("One Particle Found.");
              }
              }
          else if (particleNumber == 0){
              goalsThere = false;
              for(int i=0;i<10;i++){
              System.out.println("Zero Particle Found.");
                 }
              }
          else{
          goalsThere = false;
              for(int i=0;i<10;i++){
              System.out.println("Other Num Of Particle Found.");
                 }
              }
          }
 
        catch (NIVisionException ex) {
                ex.printStackTrace();   //Gets the errors which have been thrown by the methods in hotGoalPresent.
   }
       catch (AxisCameraException ex) {
                ex.printStackTrace();   //Gets the errors which have been thrown by the methods in hotGoalPresent.
   }
     
     
   return goalsThere;
   }
      /*double computeDistance (BinaryImage findTape, ParticleAnalysisReport report, int particleNumber, boolean outer) throws NIVisionException {
            double rectShort, height;
             final int Y_IMAGE_RES = 240;        //X Image resolution in pixels. 120, 240, 480
             final double VIEW_ANGLE = 49;       //Axis M1013
             int targetHeight;
       rectShort = NIVision.MeasureParticle(findTape.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
            //using the smaller of the estimated rectangle short side and the bounding rectangle height results in better performance
            //on skewed rectangles
            height = Math.min(report.boundingRectHeight, rectShort);
            targetHeight = outer ? 29 : 21;
            return Y_IMAGE_RES * targetHeight / (height * 24 * Math.tan(VIEW_ANGLE*Math.PI/(360)));
   }
      */
    
 private double computeDistance (BinaryImage findTape, ParticleAnalysisReport ParticleReport, int particleNumber) throws NIVisionException {
        double rectLong, height;
        int targetHeight;
        
        rectLong = NIVision.MeasureParticle(findTape.image, particleNumber, false, NIVision.MeasurementType.IMAQ_MT_EQUIVALENT_RECT_SHORT_SIDE);
        //using the smaller of the estimated rectangle long side and the bounding rectangle height results in better 
        //performance on skewed rectangles
        height = Math.min(ParticleReport.boundingRectHeight, rectLong);
        targetHeight = 32;
        
        return 320 * targetHeight / (height * 24 * Math.tan(4 * Math.PI / (360)));
    }
   }
