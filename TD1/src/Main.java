
public class Main {

	public static void main(String[] args) throws Exception {
		Connexion test = new Connexion();
		test.uneRequete();
		Catégorie yolo = new Catégorie(5, "cc", "test.png");
		//yolo.post();
		yolo.del();
	}

}
