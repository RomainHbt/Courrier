package city;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exception.NoSuchMoneyException;

import letter.Letter;


/**
 * The city which represents a city for the simulation
 * This class contains the main of the application
 * @author hembert meyer
 */

public class City
{
	private String nom;
	private List<Inhabitant> inhabitants;
	private List<Letter<?>> postBox;
	public static final Random random = new Random();
	
	/**
	 * Constructor of a city
	 * @param name The name of the city
	 */
	public City(String name) {
		this.inhabitants = new ArrayList<>();
		this.postBox = new ArrayList<>();
	}
	
	/**
	 * Add a letter in the city's postbox
	 * @param l The letter to be send
	 */
	public void sendLetter(Letter<?> l) {
		this.postBox.add(l);
	}
	
	/**
	 * Distribute all letters from the postbox
	 */
	public void distributeLetter() {
		// The postman takes letters in the postbox
		List<Letter<?>> postmanBag = new ArrayList<>();
		for (Letter<?> l : this.postBox) {
			postmanBag.add(l);
		}
		this.postBox.clear();
		
		// The postman sends letters
		for (Letter<?> l : postmanBag) {
			l.getReceiver().receiveLetter(l);
		}
	}
	
	/**
	 * Get the n inhabitant of the city
	 * @param n The number of the inhabitant
	 * @return The inhabitant number n
	 */
	public Inhabitant getInhabitant(int n) {
		return this.inhabitants.get(n-1);	
	}
	
	/**
	 * Get a random inhabitant of the city
	 * @return a random inhabitant
	 */
	public Inhabitant getRandomInhabitant() {
		return this.inhabitants.get(City.random.nextInt(this.inhabitants.size()));	
	}
	
	public String getNom(){
		return nom;
	}
	
	/**
	 * Add an inhabitant in the city
	 * @param i The inhabitant to be added
	 */
	public void addInhabitant(Inhabitant i){
		this.inhabitants.add(i);
	}

	/**
	 * Main program
	 * @param args Command's arguments
	 */
	public static void main(String[] args) {
		int nbHabitants = 100;
		int nbJours = 6;
		int day;
		
		if(args.length == 2){
			nbHabitants = Integer.parseInt(args[0]);
			nbJours = Integer.parseInt(args[1]);
		}
		
		// Creating city
		System.out.println("Creating Hazebrouck city ...");
		City c = new City("Hazebrouck");
		
		// Creating inhabitants
		System.out.println("Creating "+nbHabitants+" inhabitants ...");
		for(int i = 1; i <= nbHabitants; i++){
			Inhabitant h = new Inhabitant("habitant-"+i, c, (int) (random.nextFloat()*500));
			c.addInhabitant(h);
		}
		
		System.out.println("Mailing letters for "+nbJours+" days ...");
		
		// Start of simulation
		for(day = 1; day <= nbJours; day++){
			System.out.println("------------------------------------------\nDay "+day);
			
			// Distributing letters ...
			if(!c.postBox.isEmpty()){
				c.distributeLetter();
			}
			
			// Sending letters ...
			int nbSenders = (City.random.nextInt(10))+1; 
			for (int i = 0; i < nbSenders; i++) {
				Inhabitant sender = c.getRandomInhabitant();
				try {
					sender.sendLetter(Letter.makeLetter(sender));
				} catch (NoSuchMoneyException e) {}
			}
		}
		
		// If the postbox is not empty yet
		while(!c.postBox.isEmpty()){
			System.out.println("------------------------------------------\nDay "+day);
			// Distributing letters ...
			c.distributeLetter();
			day++;
		}
	}
}

