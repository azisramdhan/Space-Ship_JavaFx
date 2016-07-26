/*******************************************************************
* File Name 	: GameScene.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 12/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Use to create Interface that show in the Stage
*
********************************************************************/

//package view/class untuk membuat Interface
package view;

//import package
import controller.*;

//import library yang dibutuhkan program
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//class gameScene diset publik agar bisa diakses oleh class Main
public class GameScene{

	/* Attribut-atribut dengan permission type Public -> Agar bisa diakses oleh class controller dan type Global agar bisa diakses oleh semua method di dalam class GameScene */

	//deklarasi atribut window dengan tipe data objek Stage
	public Stage window;
	//deklarasi atribut tusername dengan tipe data objek TextField : untuk kolom username
	public TextField tusername;
	//deklarasi atribut play dan quit dengan tipe data objek Button
	public Button play;
	public Button quit;
	//deklarasi atribut errHandle dengan tipe data objek Alert
	public Alert errHandle;
	//deklarasi atribut gameOver dengan tipe data objek Alert
	public Alert gameOver;
	//deklarasi atribut sceneGame dengan tipe data objek Scene
	public Scene sceneGame;
	//deklarasi atribut rootGame degan tipe data objek Stage
	public Group rootGame;
	//deklarasi atribut table degan tipe data objek TableView
	TableView<Player> table;
	//deklarasi atribut sceneMenu degan tipe data objek Scene
	Scene sceneMenu;
	//deklarasi atribut player dengan tipe data objek observable list : untuk menyimpan objek class Player 
	ObservableList<Player> player;
	//deklarasi dan instansiasi atribut constant dengan tipe data objek class Constant : untuk mengatur size objek
	Constant constant = new Constant();
	//deklarasi atribut energyScore dengan tipe data objek Label, diset static agar nilai dari atribut bisa diakses tanpa menggunakan getter
	public static Label energyScore;
	//deklarasi atribut playerName dengan tipe data objek Label, diset static agar nilai dari atribut bisa diakses tanpa menggunakan getter
	public static Label playerName;


	/*************************************************************************
	 * Method untuk membuat tampilan interface menu dengan parameter atribut 
	 * window dengan tipe data objek Stage yang dikirim dari class Main
	 *************************************************************************/
	public void createInterfaceMenu(Stage window){

		//instansiasi button play dan quit
		play = new Button();
		quit = new Button();

		//instansiasi stage window
		this.window = window;

		//deklarasi dan instansiasi atribut controller dengan tipe data objek class GameController
		GameController controller = new GameController(this);

		//deklarasi dan instansiasi atribut rootMenu dengan tipe data objek Group
		Group rootMenu = new Group();
		//instansiasi atribut sceneMenu dengan tipe data objek Scene
		sceneMenu = new Scene(rootMenu,constant.stageWidth,constant.stageHeight);
		//mengganti warna pada scene
		// sceneMenu.setFill(Paint.valueOf("#BA8BAF"));

		//deklarasi dan instansiasi atribut title dengan tipe data objek Label : untuk title game dalam scene
		Label title = new Label("FIGHT THE CHALLENGE");
		//mengatur jenis font text pada Label
		title.setFont(Font.font("Times New Roman", FontWeight.BOLD, constant.fontTitleSize));

		//deklarasi dan instansiasi atribut usernameColumn dengan tipe data objek TableColumn : untuk column dalam TableView
		TableColumn<Player,String> usernameColumn = new TableColumn<>("Username");
		//set width column
		usernameColumn.setPrefWidth(constant.columnWidth);
		usernameColumn.setMinWidth(constant.columnWidth);
		usernameColumn.setMaxWidth(constant.columnWidth);
		usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

		//instansiasi atribut errHandle : untuk menghandle ketika user mencoba masuk dengan kolom username yang kosong
		errHandle = new Alert(AlertType.INFORMATION);
		errHandle.setTitle("Warning");
		errHandle.setHeaderText(null);
		errHandle.setContentText("Username is not valid !");

		//instansiasi atribut gameOver : untuk memberi tahu bahwa permainan telah selesai
		gameOver = new Alert(AlertType.INFORMATION);
		gameOver.setTitle("Game Over");
		gameOver.setHeaderText(null);
		gameOver.setContentText("Thanks for Playing\nClick OK and Press Space Button to Exit");

		//deklarasi dan instansiasi atribut energyColumn dengan tipe data objek TableColumn : untuk column dalam TableView
		TableColumn<Player,Integer> energyColumn = new TableColumn<>("Energy");
		//set width column
		energyColumn.setPrefWidth(constant.columnWidth);
		energyColumn.setMinWidth(constant.columnWidth);
		energyColumn.setMaxWidth(constant.columnWidth);
		energyColumn.setCellValueFactory(new PropertyValueFactory<>("energy"));

		//instansiasi atribut table dengan tipe data objek TableView : untuk menampilkan username dan energy player
		table = new TableView<>();

		//memasukkan column yang telah dibuat ke dalam table
		table.getColumns().addAll(usernameColumn,energyColumn);
		//memanggil method untuk memasukkan nilai kedalam tabel
		setTableValue();

		//deklarasi dan instansiasi atribut hb dengan tipe data objek HBox : untuk tempat kolom username dan button play dan quit
		HBox hb = new HBox();

		//deklarasi dan instansiasi atribut username dengan tipe data objek Label : untuk keterangan kolom username (sebelah kiri)
		Label username = new Label("Username");
		//mengatur jenis font text pada Label
		username.setFont(Font.font("Times New Roman", constant.fontSize));

		//instansiasi atribut tusername
		tusername = new TextField();
		//mengatur jenis font text pada Label
		tusername.setFont(Font.font("Times New Roman", constant.fontSize));

		//set text pada Button 
		play.setText("Play");
		//mengatur jenis font text pada Button
		play.setFont(Font.font("Times New Roman", constant.fontSize));

		//set text pada Button 
		quit.setText("Exit");
		//mengatur jenis font text pada Button
		quit.setFont(Font.font("Times New Roman", constant.fontSize));

		//mengatur spacing pada HBox
		hb.setSpacing(constant.hboxSpacing);
		//memasukkan objek ke dalam HBox
		hb.getChildren().addAll(username,tusername,play,quit);

		//deklarasi dan instansiasi atribut vbox dengan tipe data objek VBox : untuk tempat hb_username, hb_button dan table
		VBox vbox = new VBox();
		//mengatur spacing pada VBox
		vbox.setSpacing(constant.vboxSpacing);
		//set koordinat X dan Y VBox
		vbox.setLayoutY(40);
		vbox.setLayoutX(100);
		//memasukkan objek ke dalam VBox
		vbox.getChildren().addAll(title,table,hb);

		//deklarasi dan instansiasi atribut credit1 dengan tipe data objek Label : untuk keterangan Credit untuk Aset sound
		Label credit1 = new Label("Music by :");
		//mengatur jenis font text pada Label
		credit1.setFont(Font.font("Times New Roman", constant.fontSize));

		//deklarasi dan instansiasi atribut credit dengan tipe data objek Label : untuk keterangan Credit untuk Aset sound
		Label credit2 = new Label("www.matthewklingensmith.com");
		//mengatur jenis font text pada Label
		credit2.setFont(Font.font("Times New Roman", constant.fontSize));

		//deklarasi dan instansiasi atribut vbox dengan tipe data objek VBox : untuk tempat hb_username, hb_button dan table
		VBox vbox2 = new VBox();
		//mengatur spacing pada VBox
		vbox2.setPadding(new Insets(50,0,0,10));
		//memasukkan objek ke dalam VBox
		vbox2.getChildren().addAll(credit1,credit2);

		//instansiasi atribut hbox
		HBox hbox = new HBox();
		hbox.setSpacing(constant.hboxSpacing);
		//set koordinat X dan Y HBox
		hbox.setLayoutY(40);
		hbox.setLayoutX(80);
		//memasukkan objek ke daam hbox
		hbox.getChildren().addAll(vbox,vbox2);

		//memasukkan objek ke dalam group dimana sceneMenu berada di dalam grup tersebut
		((Group)sceneMenu.getRoot()).getChildren().addAll(hbox);
		//atau bisa juga rootMenu.getChildren().addAll(title,vbox);

		//memanggil method setStage
		setStage(window,sceneMenu);
	}

	/***********************************************
	 * Method untuk membuat tampilan interface game 
	 ***********************************************/
	public void createInterfaceGame(){

		//instansiasi atribut rootMenu dengan tipe data objek Group
		rootGame = new Group();
		//instansiasi atribut energyScore
		energyScore = new Label();
		energyScore.setFont(Font.font("Times New Roman", constant.fontSize));
		/* Mengatur koodinat x dan y Label energyScore */
		energyScore.setLayoutX(constant.stageWidth - (constant.columnWidth/3 + 20));
		energyScore.setLayoutY(constant.meteorHeight/2);

		//instansiasi atribut playerName
		playerName = new Label();
		playerName.setFont(Font.font("Times New Roman", constant.fontSize));
		/* Mengatur koodinat x dan y Label playerName */
		playerName.setLayoutX(constant.stageWidth/20);
		playerName.setLayoutY(constant.meteorHeight/2);

		//instansiasi atribut sceneMenu dengan tipe data objek Scene
		sceneGame = new Scene(rootGame,constant.stageWidth,constant.stageHeight);
		//set scene untuk window
		window.setScene(sceneGame);
		//tampilkan window
		window.show();

	}
	
	/***************************************************
	 * Method untuk mengganti scene menjadi scene Menu
	 ***************************************************/
	public void backToMenu(){
		//set scene untuk window
		window.setScene(sceneMenu);
		//tampilkan window
		window.show();
	}

	/****************************************************
	 * Method untuk men set nilai default atribut Stage
	 ****************************************************/
	void setStage(Stage window, Scene sceneMenu){

		//instansiasi atribut global window 
		this.window = window;
		//set judul pada title bar window
		window.setTitle("FTC Version 1.1.0");
		//agar window tidak bisa di resize
		window.setResizable(false);
		//set scene untuk window
		window.setScene(sceneMenu);
		//tampilkan window
		window.show();

	}

	/****************************************************
	 * Method untuk mengambil nilai dari atribut player
	 *****************************************************/
	ObservableList<Player> getPlayer(){
		//kembalikan nilai atribut observablelist player
		return this.player;
	}

	/**************************************************************
	 * Method untuk men set nilai atribut player dengan nilai yang 
	 * diambil dari database melalui class controller
	 **************************************************************/
	public void setPlayer(String username[], String energy[], int record){
		this.player = FXCollections.observableArrayList();
		for(int i=0;i<record;i++){
			//menambahkan data yang telah diambil dari databse ke objek di observable list
			this.player.add(new Player(username[i],Integer.parseInt(energy[i])));

			//menampilkan data dari database yang dikirim oleh class controller di log
			System.out.println((i+1)+" Username : "+username[i]);
			System.out.println("  Energy of "+username[i]+" : "+energy[i]);
		}
	}


	/******************************************************************************
	 * Method untuk menghapus semua nilai atribut observable list player dan table
	 ******************************************************************************/
	public void clearPlayerValue(){
			//hapus semua nilai atribut player
			player.clear();
			//hapus semua isi tabel
			table.getItems().clear();
	}


	/******************************************************************************
	 * Method untuk menngisi nilai ke tabel dari atribut observable list player
	 ******************************************************************************/
	public void setTableValue(){
		//set isi dari table 
		table.setItems(getPlayer());
	}
}