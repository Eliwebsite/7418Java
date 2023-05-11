
package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class Robot extends TimedRobot {

  // Drivetrain 
  CANSparkMax left1 = new CANSparkMax(4, MotorType.kBrushed);
  CANSparkMax left2 = new CANSparkMax(3, MotorType.kBrushed);
  MotorControllerGroup  m_Left = new MotorControllerGroup(left1, left2); 
  CANSparkMax right1 = new CANSparkMax(2, MotorType.kBrushed);
  CANSparkMax right2 = new CANSparkMax(1, MotorType.kBrushed);
  MotorControllerGroup m_Right = new MotorControllerGroup(right1, right2);
  DifferentialDrive m_robotDrive = new DifferentialDrive(m_Left, m_Right);

  // Intake 
  CANSparkMax intake = new CANSparkMax(9, MotorType.kBrushed);
  CANSparkMax intakewheels = new CANSparkMax(8, MotorType.kBrushed);

  // Shooter 
  CANSparkMax shooterwheels = new CANSparkMax(5, MotorType.kBrushed);
  CANSparkMax shooterwheels1 = new CANSparkMax(6, MotorType.kBrushed);
  CANSparkMax shooterbelt = new CANSparkMax(7, MotorType.kBrushed);

  // Controller 
  
  XboxController m_one = new XboxController(0); // Drive 1 Controller Setup
  XboxController m_two = new XboxController(1); // Driver 2 Controller Setup
  

  @Override
  public void robotInit() {
    //inversts  motors
  m_Right.setInverted(true); 
  shooterwheels.setInverted(true);
  }
  // This function is called once when teleop is enabled. 
  @Override
  public void teleopInit() {}

  // This function is called periodically during operator control.
  @Override
  public void teleopPeriodic() {
   
    // Drive Train Control System

    m_robotDrive.arcadeDrive(-m_one.getLeftY() / 1.25, -m_one.getLeftX() / 1.25); 

    
    //get buttons
    boolean oneRightBumper = m_one.getRightBumper(); 
    boolean oneLeftBumper = m_one.getLeftBumper(); 
    boolean xButton =  m_one.getXButton();
    boolean yButton =  m_one.getYButton();
    boolean aButton = m_one.getAButton();
    boolean bButton = m_one.getBButton();
    
   
    //intake system
    intake.set(0);                 
    if (oneRightBumper == true) {
      intake.set(-1.0);    
      intakewheels.set(-1.0);         
    }
    if (oneLeftBumper == true) {
      intake.set(1.0);   
      intakewheels.set(1.0);         
    }
    if (bButton == true) {
      intake.set(0.0);
    }

    // Shooter System
    
    shooterbelt.set(0);      
    shooterwheels.set(0);
    shooterwheels1.set(0);

    if (yButton == true) {
      shooterbelt.set(1.0);            
    }
    if (xButton == true) {
      shooterwheels.set(1.0);    
      shooterwheels1.set(1.0);       
    }
    if (aButton == true) {
      shooterbelt.set(0.0);
      shooterwheels.set(0.0);
      shooterwheels.set(0.0);
    }
  }

  /** This function is called once when the robot is disabled. 
  @Override
  public void disabledInit() {}
  @Override
  public void disabledPeriodic() {}
  @Override
  public void testInit() {}
  @Override
  public void testPeriodic() {}
  @Override
  public void simulationInit() {}
  @Override
  public void simulationPeriodic() {}
  @Override
  public void teleopInit() {}
  */
}

