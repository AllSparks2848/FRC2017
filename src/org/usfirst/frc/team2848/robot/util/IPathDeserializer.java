package org.usfirst.frc.team2848.robot.util;

/**
 * Interface for methods that deserializes a Path or Trajectory.
 * 
 *
 */
public interface IPathDeserializer {
  
  public Path deserialize(String serialized);
}
