package com.example.marsrover;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*REST API to get the calculated path*/
@RestController
@RequestMapping("/marsRover")
public class MarsRoverController {
	
	/*REST API to get the calculated path based on the input string ex : directionString = LLM*/
	@GetMapping(value="/getShortestpath/{directionString}",produces=MediaType.APPLICATION_JSON_VALUE)
	public int getShortestpath(@PathVariable("directionString") String directionString) {
		try {
				return this.calculatePath(directionString);
		}
		catch(Exception e) {
			e.printStackTrace();
			/*return 0 in case if exception occured*/
			return 0;
		}
	}
	/*method to calculated path based on the input string*/
	private int calculatePath(String directionString) throws Exception{
		/*considering currentDirection = 1 (+X), currentDirection = 2 (+Y) , currentDirection = 3 (-X) , currentDirection = 4 (-Y)*/
		int currentDirection = 1;
		/*setting initial position as (0,0)*/
		int currentX=0;
		int currentY=0;
		if(directionString!=null && directionString.length()>0) {
			for(int i=0;i<directionString.length();i++) {
				switch(directionString.charAt(i)) {
				case 'L':
					/*rotate direction to left by adding 1 to current direction*/
					if(currentDirection==4) {
						currentDirection = 1;
					}
					else {
					currentDirection++;
					}
					break;
				case 'R':
					/*rotate direction to right by subtracting 1 from current direction*/
					if(currentDirection==1) {
						currentDirection = 1;
					}
					else {
					currentDirection--;
					}
					break;
				case 'M':
					/*adding 1 unit movement based on the current direction for X axis and Y axis.*/
					switch(currentDirection) {
					case 1:
						currentX++;
						break;
					case 2:
						currentY++;
						break;
					case 3:
						currentX--;
						break;
					case 4:
						currentY--;
						break;
					}
					break;
				}
			}
		}
		/*taking absolute value of x and y axis to calculate total distance*/
		return Math.abs(currentX) + Math.abs(currentY);
	
	}
}
