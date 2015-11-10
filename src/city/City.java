package city;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import letter.Letter;


/**
 * The city which represents a city for the simulation
 * This class contains the main of the application
 * @author hembert & meyer
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
	 * Add an inhabitant in the city
	 * @param i The inhabitant to be added
	 */
	public void addInhabitant(Inhabitant i){
		this.inhabitants.add(i);
	}

	/**
	 * Main program
	 */
	public static void main(String[] args) {
		int nbHabitants = 100;
		int nbJours = 6;
		int day;
		
		// Creating city
		System.out.println("Création de la ville d'Hazebrouck ...");
		City c = new City("Hazebrouck");
		
		// Creating inhabitants
		System.out.println("Création de "+nbHabitants+" habitants ...");
		for(int i = 1; i <= nbHabitants; i++){
			Inhabitant h = new Inhabitant("habitant-"+i, c, (int) (random.nextFloat()*500));
			c.addInhabitant(h);
		}
		
		System.out.println("Distribution du courrier pendant "+nbJours+" jours ...");
		
		// Start of simulation
		for(day = 1; day <= nbJours; day++){
			System.out.println("------------------------------------------\nJour "+day);
			
			// Distributing letters ...
			if(!c.postBox.isEmpty()){
				c.distributeLetter();
			}
			
			// Sending letters ...
			// TODO Generate and send random letters
		}
		
		// If the postbox is not empty yet
		while(!c.postBox.isEmpty()){
			System.out.println("------------------------------------------\nJour "+day);
			// Distributing letters ...
			c.distributeLetter();
			day++;
		}
	}
}

