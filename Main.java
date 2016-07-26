/*******************************************************************
* File Name 	: Main.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 12/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Use to be central of the MVC / as Main Program
*
********************************************************************/

//import package view
import view.GameScene;

//import library yang dibutuhkan program
import javafx.stage.Stage;
import javafx.application.Application;

//class Main
public class Main extends Application{

	/*************************************
	 * Method untuk menjalankan aplikasi
	 **************************************/
	public static void main(String[] args){
		launch(args);
	}

	/****************************************************************
	 * Method untuk memanggil class GameScene (Class View)
	 * Overriding dari class Application
	 * Method ini akan dipanggil otomatis ketika program dijalankan
	 ****************************************************************/
	@Override
	public void start(Stage window){
		//deklarasi dan instansiasi atribut openGame dengan tipe data objek class GameScene 
		GameScene openGame = new GameScene();
		//memanggil method yang ada di dalam class GameScene untuk membuat interface Menu
		openGame.createInterfaceMenu(window);
	}
}


/*
Saya [Azis Ramdhan Darojat] tidak melakukan kecurangan yang dispesifikasikan pada tugas masa depan PBO pada saat mengerjakan Tugas Masa Depan PBO. Jika saya melakukan kecurangan maka Allah/Tuhan adalah saksi saya, dan saya bersedia menerima hukumanNya. Aamiin.
*/