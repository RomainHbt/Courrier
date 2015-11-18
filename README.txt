Projet du courrier en java
Hembert Romain - Meyer Hélène
18/11/2015

=== Introduction ===

Ce projet est une simulation de la distribution de courrier: chaque jour, des habitants envoient des courriers à d'autres habitants. Ces courriers sont donc placés dans des boites aux lettres et le courrier est distribué le lendemain. Ici, les villes sont considérées comme des bureaux distributeurs.

=== Usage ===

* Démarrer la simulation *

	Démarrer la simulation en lançant le .jar :
	$java -jar tp03-hembert-meyer.jar [nbHabitants] [nbJours]
	ou alors :
	./tp03-hembert-meyer.jar [nbHabitants] [nbJours]
	(Ne pas oublier de rajouter les droits)

* Remarque * 

	Aucune remarque

=== Architecture ===

	Classe Inhabitant représente un habitant

	Classe City contient le main, pour lequel il prend en parametre les arguments passés lors du démarrage de la simulation. Celui-ci crée une ville et des habitants. De plus, il crée de nombreuses lettres que diverses habitants envoient à d'autres. On peut lire le résultat de cette simulation.

	Polymorphisme :
		Interface Content contenant le contenu d'une lettre. Ici, nous avons deux types de contenu : les montants en Integer et le texte en String. 

		Classe abstraite Letter<C extends Content>(type générique) qui est un contenu (interface Content) avec une méthode abstraite action() qui permet de faire toutes les actions necessaires apres la reception de celle-ci. Une autre classe abstraite hérite de cette classe : SpecialLetter<C extends Content>. Cette classe abstraite permet de gérer les cas comme UrgentLetter et RegisteredLetter. Ces constructeurs prennent donc en parametre une lettre et la méthode action appelle la methode action() de la lettre et cette méthode est donc surchargée dans la classe correspondante. Deux autres classes héritent de Letter<C extends Content>  : SimpleLetter et PromissoryNote qui utilisent ContentString et ContentAmount respectivement. De plus, des classes comme ThankYouLetter et AcknowledgmentOfReceipt heritent de SimpleLetter pour la simulation.
	
=== Code sample ===

La partie du code qui gère la simulation de l'envoie du courrier est dans le main se trouvant dans la classe City.

	public static void main(String[] args) {
		[...]
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
				} catch (NoSuchMoneyException e) {
					System.out.println(sender.getName()+" can't send a letter : he hasn't enough money :(");
				}
			}
		}
		[...]
	}

La méthode sendLetter(Letter<?> letter) se trouvant dans la classe Inhabitant. Celle-ci est la même pour chaque lettre. Et renvoie une exception s'il n'y a pas assez d'argent.

		public void sendLetter(Letter<?> letter) throws NoSuchMoneyException {
			// gère si la lettre peut etre envoyee, c'est a dire s'il y a assez d'argent sur le compte.
			if (letter.possibleToSend()) { 
				System.out.println(letter.getSendDescription());
				this.withdraw(letter.getPrice());
				this.city.sendLetter(letter);
			} else {
				// exception lancée lorqu'il 
				throw new NoSuchMoneyException();
			}
		}

Design Pattern : Decorator
Ici le décorateur est la classe abstraite SpecialLetter<C extends Content>. L'élément décoré est donc la lettre mis en parametre du constructeur (un attribut letter le contient). Le travail est délégué au décoré (letter). La méthode action() est chargée dans les sous-classes de SpecialLetter : 

	/* Dans la classe SpecialLetter<C extends Content>
	 * @see letter.Letter#action()
	 */
	public void action() {
		this.letter.action();
	}
	

	/* Dans la classe RegisteredLetter<C extends Content> héritant de SpecialLetter<C>
	 * @see letter.Letter#action()
	 */
	@Override
	public void action() {
		super.action();
		sendAcknoledmentOfReceipt();
	}
	

	
