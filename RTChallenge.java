//RTChallenge version 1.0 BETA
import java.util.Scanner;
import java.util.Random;

public class RTChallenge {
	public static void main(String[] args) {
		welcome();
		/*nbrPlayer();*/

		/* On affiche les règles et le but du jeu */
		System.out.println(ANSI_RED + "Voici les regles et le but du jeu !" + ANSI_RED);
		rule();

		String[] tab_player = nbrPlayer();/*initiablisation tab player et demande nbr de joueur(s)*/

		Scanner sc4 = new Scanner(System.in);
		System.out.print(ANSI_PURPLE + "Commencer le jeu ? o=oui et n=non " + ANSI_RESET);
		String startGame = sc4.nextLine();
		if(startGame.indexOf('n')!=-1){
			System.out.println(ANSI_RED + "Partie annulee..." + ANSI_RESET);
		}else{
			game(tab_player);
		}
	}

	public static void welcome() {
		System.out.println("--------------------------------");
		System.out.println("| " + ANSI_GREEN + "Bienvenue sur RT Challenge 1.0 BETA :)" + ANSI_RESET + " |");
		System.out.println("--------------------------------");
	}

	public static String[] nbrPlayer() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Combien de joueur souhaite jouer ? ");
		int nbrPlay = sc.nextInt();
		String[] tabplay = new String[nbrPlay];
		int player = 1;
		for (int i = 0; i < nbrPlay; i++) {
			Scanner sc2 = new Scanner(System.in);
			System.out.print("Joueur "+player+" ? ");
			String nameplayer = sc2.nextLine();
			player = player + 1;
			tabplay[i] = nameplayer;
		}
		System.out.println("Bonjour, voici la liste des joueurs :");
		for (int i = 0; i < tabplay.length; i++) {
			System.out.println(ANSI_BLUE + "Le joueur " + (i+1) + " est " + ANSI_RESET + ANSI_YELLOW + tabplay[i] + ANSI_RESET);
		}
		return tabplay;
	}

	public static void rule() {
		System.out.println(ANSI_CYAN + "BUT DU JEU :" + ANSI_RESET);
		System.out.println("Il peut y avoir autant de joueurs que vous le souhaitez.");
		System.out.println("Mais un seul peut gagner, et il s'agit du premier qui arrive a " + scoreForWin + " points !");
		System.out.println(ANSI_CYAN + "FONCTIONNEMENT :" + ANSI_RESET);
		System.out.println("Les questions sont aleatoires, il est possible que plusieurs joueurs tombent sur la même question.");
		System.out.println("---------------------------------");
		System.out.println("ATTENTION ! : Veuillez écrire exactement les reponses comme elles sont proposees sinon la bonne reponse ne sera pas validee, donc attention aux majuscules et aux minuscules !");
		System.out.println("---------------------------------");
	}

	public static void game(String[] tabplay) {
		String[] question = {
			"Que signifie R&T ?",
			"En binaire combien vaut 7 ?",
			"Quelle sont les classes d'adresse IP ?",
			"Commande pour creer un fichier .class suite a un fichier .java ?",
			"Commande pour compiler un fichier .java ?",
			"Le HTML est un langage de...",
			"La balise HTML <p></p> est une balise de type...",
			"En HTML la bonne syntaxe est...",
			"En JAVA la bonne suntaxe est...",
			"Comment on defini un tableau de chaine en JAVA ?"
		};
		String[] reponseGood = {
			"Reseaux et telecoms",
			"0111",
			"A B C D E",
			"javac",
			"java",
			"balise",
			"bloc",
			"<b><p></p></<b>",
			"import fichier;",
			"String[] chaine;"
		};
		String[] reponseNo1 = {
			"Reseaux et telegramme",
			"1000",
			"1 2 3 4 5",
			"java",
			"javac",
			"programmation",
			"ligne",
			"<b><p></b></<p>",
			"import fichier",
			"String[] = chaine;"
		};
		String[] reponseNo2 = {
			"Reseaux et telephone",
			"1110",
			"A B C D",
			"execjava",
			"execjava",
			"bloc",
			"simple balise",
			"<b p></p b>",
			"fichier import;",
			"String[] chaine"
		};
		int[] score = new int[tabplay.length];/*initialisation des score*/
		int i = 0;
		for(i = 0; i < score.length; i++ ){
			/*on définit un nbr aléatoire pour les questions*/

			/*Random r = new Random();
			int valeur = valeurMin + r.nextInt(valeurMax - valeurMin);*/
			Random r = new Random();
			int alea = 0 + r.nextInt((question.length-1) - 0);

			/*On créer et on initialise nos tableaux*/
			String[] t1 = {reponseGood[alea],reponseNo1[alea],reponseNo2[alea]};
			String[] t2 = {reponseNo1[alea],reponseGood[alea],reponseNo2[alea]};
			String[] t3 = {reponseNo2[alea],reponseNo1[alea],reponseGood[alea]};
			Random ordreQuestion = new Random();
			int aleaRep = 1 + ordreQuestion.nextInt(4 - 1);
			String request = "t"+aleaRep;
			System.out.print(ANSI_YELLOW+"Question pour "+tabplay[i]+" : "+ANSI_RESET);
			System.out.println(question[alea]);
			if(request.equals("t1")){
				System.out.println(t1[0] + " - " + t1[1] + " - " + t1[2]);
			}else if(request.equals("t2")){
				System.out.println(t2[0] + " - " + t2[1] + " - " + t2[2]);
			}else if(request.equals("t3")){
				System.out.println(t3[0] + " - " + t3[1] + " - " + t3[2]);
			}
			Scanner reponse_player = new Scanner(System.in);
			System.out.print("Reponse : ");
			String rep_player = reponse_player.nextLine();

			if(rep_player.equals(reponseGood[alea])){
				score[i] = score[i] + 1;
				System.out.println(ANSI_GREEN + "Bonne reponse ! +1 point" + ANSI_RESET);
			}else{
				System.out.println(ANSI_RED + "Reponse incorrect ! +0 point" + ANSI_RESET);
			}

			System.out.println("--------------------------------------------");
			System.out.println(ANSI_YELLOW + "Score(s) :" + ANSI_RESET);
			for(int e = 0; e<tabplay.length; e++){
				System.out.println(ANSI_BLUE + tabplay[e] + " : " + score[e] + " point(s)" + ANSI_RESET);
			}
			System.out.println("--------------------------------------------");

			if(score[i] == scoreForWin){
				System.out.println(ANSI_YELLOW + tabplay[i] + " remporte la partie !" + ANSI_RESET);
				break;/*on sors de la boucle*/
			}
			int tab_max = (tabplay.length)-1;
			if(i == tab_max){
				i = -1;
			}
		}
	}

	public static final int scoreForWin = 5;/*score pour remporter la partie*/
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
}