
/********************************************************************************
* File Name 	: Constant.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 12/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Save much String, int or double value that needed in program
*
*********************************************************************************/

//package view/class untuk membuat Interface
package view;

//class Constant di set public agar bisa diakses oleh class lain
public class Constant{

	/* Deklarasi dan inisialisasi atribut global dengan permission type diset public agar bisa diakses oleh class lain dan static agar nilai dari atribut bisa diakses tanpa harus menggunakan getter */

	// : kecepatan meteor saat turun
	public static int gravity = 3;
	// : kecepatan pergerakan pesawat
	public static int velocity = 5;

	// : ukuran font text dalam game selain text judul
	public static int fontSize = 15;
	// : ukuran font text judul
	public static int fontTitleSize = 30;
	
	// : meteor akan diinstansiasikan pada kelipatan 20 pada class Controller
	public static int meteorTime = 20;
	
	// : ukuran spacing pada vbox
	public static int vboxSpacing = 20;
	// : ukuran spacing pada hbox
	public static int hboxSpacing = 35;
	
	// : ukuran lebar column pada table
	public static int columnWidth = 300;
	
	// : koordinat x pesawat
	public static int xShipCoordinate = 350;
	// : koordinat x meteor
	public static int yShipCoordinate = 450;
	
	// : lebar scene dalam game
	public static double stageWidth = 1000;
	// : tinggi scene dalam game
	public static double stageHeight = 600;
	
	// : lebar pesawat
	public static double shipWidth = 115.1;
	// : tinggi pesawat
	public static double shipHeight = 82.5;
	
	// : lebar meteor
	public static double meteorWidth = 70;
	// : tinggi meteor
	public static double meteorHeight = 60;
	
	// : nama file music scene Menu
	public static String musicMenu = "";
	// : nama file music scene Games
	public static String musicGames = "";
}