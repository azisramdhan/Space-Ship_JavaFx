/***********************************************************************
* File Name 	: GameController.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 12/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Handling such as Event Handler, Logical Operation etc.
*
*************************************************************************/

//package controller/ class untuk mengatur proses jalannya program
package controller;

//import package
import view.*;
import model.*;

//import library yang dibutuhkan program
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.util.ArrayList;

//class Controller diset public agar bisa diakses oleh class GameScene (View)
public class GameController{

	/* Deklarasi atribut global */

	//deklarasi atribut ship dengan tipe data objek class Ship
	Ship ship;
	//deklarasi atribut stage dengan tipe data objek class Stage
	Stage window;
	//deklarasi atribut xCoordinate dengan tipe data integer
	int xCoordinate;
	//deklarasi atribut yCoordinate dengan tipe data integer
	int yCoordinate;
	//deklarasi atribut gameScene dengan tipe data objek class GameScene
	GameScene gameScene;
	//deklarasi atribut dataPlayer dengan tipe data objek class Data
	Data dataPlayer;
	//deklarasi atribut arrMeteor dengan tipe data ArrayList
	ArrayList<Meteor> arrMeteor;
	//deklarasi atribut timer dengan tipe data objek AnimationTimer
	AnimationTimer timer;
	//deklarasi atribut end dengan tipe data boolean
	boolean end;
	//deklarasi atribut energyPlayer dengan tipe data integer
	int energyPlayer;
	//deklarasi atribut username dengan tipe data array of String
	String[] username; 
	//deklarasi atribut energy dengan tipe data array of String	
	String[] energy;
	//deklarasi atribut record dengan tipe data integer	
	int record;
	//deklarasi atribut usernamePlayer dengan tipe data String	
	String usernamePlayer;
	//deklarasi atribut mediaPlayerGame dengan tipe data objek MediaPlayer	
	MediaPlayer mediaPlayerGame;
	//deklarasi atribut mediaPlayerMenu dengan tipe data objek MediaPlayer	
	MediaPlayer mediaPlayerMenu;
	//deklarasi atribut TIME dengan tipe data integer
	int TIME;
	//deklarasi dan instansiasi atribut constant dengan tipe data objek class Constant : untuk mengatur size objek
	Constant constant = new Constant();

	/* Mengatur nilai awal atribut dengan nilai false */

	//deklarasi atribut left dengan tipe data boolean
	boolean left = false;
	//deklarasi atribut right dengan tipe data boolean
	boolean right = false;
	//deklarasi atribut up dengan tipe data boolean
	boolean up = false;
	//deklarasi atribut down dengan tipe data boolean	
	boolean down = false;

    /***************
     * Konstruktor
     ***************/
	public GameController(GameScene gameScene){
		
		//inisialisasi atribut gameScene dengan nilai dari parameter
		this.gameScene = gameScene;
		//instansiasi atribut dataPlayer
		dataPlayer = new Data();
		//instansiasi atribut arrMeteor
		arrMeteor = new ArrayList<>();
		//inisialisasi atribut window dengan nilai dari atribut window pada class GameScene
		window = gameScene.window;
		//memanggil method fetchData
		fetchData();
		//memanggil method handleButtonInput
		handleButtonInput(window);
		//memanggil method playSound
		//playSound();

	}

    /**********************************************************************
     * Method ini digunakan untuk menghandle action ketika button di klik
     **********************************************************************/
	void handleButtonInput(Stage window){

		//event yang dikerjakan ketika tombol play pada class GameScene di klik
		gameScene.play.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				//memanggil method login
				login();
			}
		});
		
		//event yang dikerjakan ketika tombol quit pada class GameScene di klik
		gameScene.quit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				//tutup aplikasi
				window.close();

				//menghentikan semua music yang diputar
				mediaPlayerMenu.stop();
				mediaPlayerGame.stop();

			}
		});

	}

    /*************************************************************************************
     * Method ini digunakan untuk proses timer dan event ketika tombol keyboard di klik
     *************************************************************************************/
	void startController(){

		/* "e->" adalah code program singkat dari " new EventHandler<ActionEvent>(){ @Override public void handle(ActionEvent event) " (lambda expression) */

		//mengatur event yang dikerjakan ketika tombol pada keyboard di tekan 
		gameScene.sceneGame.setOnKeyPressed(e -> {
                    switch (e.getCode()) {
                        case RIGHT:
                        	//inisialisasi atribut right
                            right = true;
                            break;
                        case LEFT:
	                        //inisialisasi atribut left
                            left = true;
                            break;
                        case UP:
                        	//inisialisasi atribut up
                            up = true;
                            break;
                        case DOWN:
	                        //inisialisasi atribut down
                            down = true;
                            break;
                    }
                });
		//mengatur event yang dikerjakan ketika tombol pada keyboard di lepas
		gameScene.sceneGame.setOnKeyReleased(e -> {
                    switch (e.getCode()) {
                    	//ketika space ditekan
                        case SPACE:
                        	//memanggil  method endGame
                        	endGame();
                            break;
                        case RIGHT:
                        	//inisialisasi atribut right
                            right = false;
                            break;
                        case LEFT:
	                        //inisialisasi atribut left
                            left = false;
                            break;
                        case UP:
	                        //inisialisasi atribut up
                            up = false;
                            break;
                        case DOWN:
                        	//inisialisasi atribut down
                            down = false;
                            break;
                    }
                });
		//instansiasi atribut timer
		timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                    	//update posisi semua meteor yang ada dalam arrayList
            	       	updateMeteor();
			        	if(TIME % constant.meteorTime == 0){
			        		//tambah meteor jika jumlah meteor yang ada dalam scene/arrayList kurang dari 15
			        		if(arrMeteor.size() < constant.stageWidth/(constant.meteorWidth - constant.meteorHeight)){
			        			//memanggil method createMeteor
			        			createMeteor();
			        		}
			        		// -- System.out.println(arrMeteor.size());
			        	}
			        	//increment atribut TIME
			        	TIME++;
			        	//jika nilai atribut left true dan koordinat pesawat > 0
                        if (left && xCoordinate > 0) {
                        	//kurangi nilai xCoordinate pesawat (untuk menggerakan pesawat ke kiri)
							xCoordinate -=constant.velocity;
							//ubah koordinat x pesawat
							ship.moveCanvasX(xCoordinate);
                        }
                        //jika nilai atribut right true dan koordinat pesawat < lebar scene - lebar pesawat
                        if (right && xCoordinate < constant.stageWidth - constant.shipWidth) {
                        	//tambah nilai xCoordinate pesawat (untuk menggerakan pesawat ke kanan)
                           	xCoordinate +=constant.velocity;
                           	//ubah koordinat x pesawat
							ship.moveCanvasX(xCoordinate);
                        }
                        //jika nilai atribut up true dan koordinat pesawat > 0
                        if (up && yCoordinate > 0) {
                        	//kurangi nilai yCoordinate pesawat (untuk menggerakan pesawat ke atas)
							yCoordinate -=constant.velocity;
							//ubah koordinat y pesawat
							ship.moveCanvasY(yCoordinate);
                        }
                        //jika nilai atribut down true dan koordinat pesawat < tinggi scene - tinggi pesawat
                        if (down && yCoordinate < constant.stageHeight - constant.shipHeight) {
                        	//tambah nilai yCoordinate pesawat (untuk menggerakan pesawat ke bawah)
                           	yCoordinate +=constant.velocity;
                           	//ubah koordinat y pesawat
							ship.moveCanvasY(yCoordinate);
                        }
                    }
                };
        //memulai timer
	    timer.start();
	}

    /*************************************************************
     * Method ini digunakan untuk mengambil data dari class Data
     *************************************************************/
	void fetchData(){
		//memanggil method fetch pada class Data
		dataPlayer.fetch();
		//tampung nilai dari class Data ke dalam atribut global
		username = dataPlayer.getUsername();
		energy = dataPlayer.getEnergy();
		record = dataPlayer.getRecord();
		//tambahkan nilai dari class Data ke dalam observable list untuk ditampilkan dalam table
		gameScene.setPlayer(username,energy,record);
	}

    /*******************************************************************
     * Method ini digunakan untuk cek username dan login (ubah scene)
     *******************************************************************/
	void login(){
		//inisialisasi atribut usernamePlayer dengan nilai string dari text tusername pada class GameScene
		usernamePlayer = gameScene.tusername.getText();
		//deklarasi dan inisialisasi (dengan nilai dari fungsi check pada class Data) atribut isPlayerNew dengan tipe data Boolean
		Boolean isPlayerNew = dataPlayer.check(usernamePlayer);
		
		//jika usernamePlayer kosong
		if(usernamePlayer.equals("")){
			//tampilkan pesan peringatan
			gameScene.errHandle.showAndWait();
		//jika usernamePlayer tidak kosong 
		}else{
			//memanggil method resetCoordinate
			resetCoordinate();
			//memanggil method createInterfaceGame pada class GameScene
			gameScene.createInterfaceGame();
			//memanggil method createShip
			createShip();
			//inisialisasi nilai dari atribut end
			end = false;
			//memanggil method start controller
			startController();
			//stop music mediaPlayerMenu 
			mediaPlayerMenu.stop();
			//mengatur music agar terus berputar sampai dihentikan (stop)
			mediaPlayerGame.setOnEndOfMedia(new Runnable() {
		       public void run() {
		         mediaPlayerGame.seek(Duration.ZERO);
		       }
		   	});
		   	//putar music mediaPlayerGame
			mediaPlayerGame.play();
			//jika nilai atribut isPlayerNew bernilai true
			if(isPlayerNew){
				//memanggil method insert pada class Data usernamePlayer
				dataPlayer.insert(usernamePlayer);
			//jika nilai dari atribut isPlayerNew bernilai false
			}else{
				// -- System.out.println(usernamePlayer);
				//perulangan sebanyak data yang telah diambil dari class Data
				for(int i = 0; i < record; i++){
					// -- System.out.println(username[i]);
					if(username[i].equals(usernamePlayer)){
						//inisialisasi atribut energyPlayer dengan nilai integer dari atribut energy[i]
						energyPlayer = Integer.parseInt(energy[i]);
						//update atribut energyScore pada class GameScene dengan tulisan "Energy" dan "energy" dari Player
						gameScene.energyScore.setText("Energy : " + energyPlayer);
						//update atribut playerName pada class GameScene dengan tulisan "Energy" dan "energy" dari Player
						gameScene.playerName.setText("Player : " + usernamePlayer);
						//memasukkan objek energyScore dan playerName ke dalam group rootGame
						gameScene.rootGame.getChildren().addAll(gameScene.energyScore,gameScene.playerName);
						// -- System.out.println(energyPlayer);
					}
				}
			}
		}

		
	}

    /*****************************************************
     * Method ini digunakan untuk menambahkan meteor baru
     * ke dalam group rootGame dengan koodinat x random
     ******************************************************/
	void createMeteor(){
		//instansiasi atribut meteor rootGame dari class GameScene
		Meteor meteor = new Meteor(gameScene.rootGame);
		//mengatur posisi/ koordinat x meteor menjadi random
		meteor.setX(Math.random() * (constant.stageWidth - constant.meteorWidth));
		//tambahkan objek meteor yang baru ke dalam ArrayList arrMeteor
		arrMeteor.add(meteor);
	}



    /*******************************************************
     * Method ini digunakan untuk update posisi/
     * koordinat y semua meteor dalam scene, cek posisi 
     * meteor dan pesawat (untuk mengetahui status tabrakan)
     ********************************************************/
	void updateMeteor(){
		//deklarasi dan instansiasi atribut temp dengan tipe data ArrayList
		ArrayList<Meteor> temp = new ArrayList<>();
		//perulangan sebanyak objek meteor yang ada dalam ArrayList arrMeteor
		for(Meteor meteor : arrMeteor){
			//ubah posisi/koordinat meteor (menggerakan meteor ke bawah)
			meteor.setY(meteor.getY()+constant.gravity);
			//jika meteor melebihi tinggi scene
			if(meteor.getY() > constant.stageHeight){
				//tampung meteor ke dalam ArrayList temp
				temp.add(meteor);
				// -- System.out.println(meteor.getY());
			}

			//jika pesawat melewati meteor (koodinat y + tinggi pesawat kurang dari koordinat y meteor)
			if((ship.getY() + ship.canvas.getHeight()) < (meteor.getY())){
				//memanggil method setStatus pada class Meteor class GameController
				meteor.setStatus(this);
			}

			//jika meteor bertabrakan dengan pesawat (dengan asumsi meteor mengenai bagian tengah pesawat)
			if(ship.getY() + ship.canvas.getHeight() > meteor.getY() && ship.getY() < meteor.getY() + meteor.canvas.getHeight() && (ship.getX() + (constant.shipWidth/2)-5 < meteor.getX() + meteor.canvas.getWidth() && ship.getX() + ship.canvas.getWidth()  - (constant.shipWidth/2)-5 > meteor.getX())){
				// -- System.out.println("Tabrakan");
				//inisialisasi atribut end dengan nilai true
				end = true;
			}
		}
		//jika atribut end bernilai true
		if(end == true){
			//inisialisasi atribut end dengan nilai false
			end = false;
			//hentikan timer
			timer.stop();
			//memanggil method shoWarning
			shoWarning();
		}
		//hampus semua objek meteor pada ArrayList temp (meteor yang melewati tinggi scene)
		for(Meteor meteor: temp){
			//remove objek meteor dari ArrayList arrMeteor
			arrMeteor.remove(meteor);
			//hapus gambar meteor pada canvas dari objek meteor dihapus
			meteor.cleanCanvas();
			//remove objek meteor dari Group rootGame pada class GameScene 
			gameScene.rootGame.getChildren().remove(meteor);
		}	
	}

    /**********************************************
     * Method ini digunakan untuk membuat pesawat
     **********************************************/
	void createShip(){
		//instansiasi atribut ship Group rootGame pada class GameScene
		ship = new Ship(gameScene.rootGame);
		/* Ubah posisi/koordinat x dan y pesawat */
		ship.moveCanvasX(xCoordinate);
		ship.moveCanvasY(yCoordinate);
	}

    /*****************************************************
     * Method ini digunakan untuk menghapus semua objek 
     * dalam arrMeteor dan semua objek dalam rootGame
     ****************************************************/
	void removeAll(){
		//menghapus semua objek dalam ArrayList arrMeteor
		arrMeteor.clear();
		//menghapus semua objek dalam Group rootGame pada class GameScene
		gameScene.rootGame.getChildren().removeAll();
	}

    /****************************************************************************
     * Method ini digunakan untuk memperbarui energy player, reset nilai dari 
     * atribut movement (right,left,up,down), reset data pada atribut table
     * dan observablelist data pada class GameScene, kembali ke scene menu
     * dan mengganti music yang berputar
     ****************************************************************************/	
	void endGame(){
		/* Mengganti nilai dari atribut movement menjadi false */
		left = false;
		right = false;
		up = false;
		down = false;

		//memanggil method update pada class Data
		dataPlayer.update(energyPlayer, usernamePlayer);
		//memanggil method clearPlayerValue pada class GameScene
		gameScene.clearPlayerValue();
		//memanggil method fetchData
		fetchData();
		//memanggil method setTableValue pada class GameScene
		gameScene.setTableValue();
		//hentikan timer
		timer.stop();
		//memanggil method removeAll
    	removeAll();
    	//memanggil method backToMenu pada class GameScene
    	gameScene.backToMenu();
    	//menghentikan music mediaPlayerGame
		mediaPlayerGame.stop();
		//putar music mediaPlayerMenu
		mediaPlayerMenu.play();
    	// -- System.out.println("Selesai");
	}

    /************************************************************
     * Method ini digunakan untuk ubah koordinat x dan y pesawat 
     * (pindahkan pesawat ke posisi awal/middle bottom of scene)
     ************************************************************/	
	void resetCoordinate(){
		//inisialisasi atribut xCoordinate
		xCoordinate = constant.xShipCoordinate;
		//inisialisasi atribut yCoordinate
		yCoordinate = constant.yShipCoordinate;
	}

   	/*****************************************************
     * Method ini digunakan untuk menambah energyPlayer
     ****************************************************/
	public void incEnergyValue(){
		//increment energyPlayer
		energyPlayer++;
		gameScene.energyScore.setText("Energy : " + energyPlayer);
		// -- System.out.println(energyPlayer);
	}

  	/*********************************************************
     * Method ini digunakan untuk deklarasi dan memutar music
     *********************************************************/
	void playSound(){

		/* inisialisasi String dengan nama file music */
		String musicGames = constant.musicGames;
		String musicMenu = constant.musicMenu;

		//deklarasi dan instansiasi atribut soundGame dengan tipe data objek Media
		Media soundGame = new Media(new File(musicGames).toURI().toString());
		//instansiasi atribut mediaPlayerGame
		mediaPlayerGame = new MediaPlayer(soundGame);

		//deklarasi dan instansiasi atribut soundMenu dengan tipe data objek Media
		Media soundMenu = new Media(new File(musicMenu).toURI().toString());
		//instansiasi atribut mediaPlayerMenu
		mediaPlayerMenu = new MediaPlayer(soundMenu);
		//mengatur music agar terus berputar sampai dihentikan (stop)
		mediaPlayerMenu.setOnEndOfMedia(new Runnable() {
	       public void run() {
	         mediaPlayerMenu.seek(Duration.ZERO);
	       }
	   	});
	   	//putar music mediaPlayerMenu
		mediaPlayerMenu.play();
	}

 	/************************************************
     * Method ini digunakan untuk menampilkan pesan 
     * Game Over ketika meteor menabrak pesawat
     ************************************************/
	void shoWarning(){
		//memanggil method showAndWait dari objek alert gameOver pada GameScene
		gameScene.gameOver.showAndWait();
	}

}