/****************************************************************************
* File Name 	: Player.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 31/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Use as objek that save username and energy of Player value
*
*****************************************************************************/


//package view/class untuk membuat Interface
package view;

//class Player
public class Player{

	/* Deklarasi atribut global */

	//deklarasi atribut username dengan tipe data String
	private String Username;
	//deklarasi atribut energy dengan tipe data Integer
	private int Energy;

	/***************
	 * Konstruktor
	 ***************/
	public Player(){
		//inisialisasi atribut username
		this.Username = "";
		//inisialisasi atribut Energy
		this.Energy = 0;
	}

	/***************
	 * Konstruktor
	 ***************/
	public Player(String Username, int Energy){
		this.Username = Username;
		this.Energy = Energy;
	}

	/* Getter dan setter atribut dalam class Player */

	public String getUsername() {
		return Username;
	}

	public int getEnergy() {
		return Energy;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public void setEnergy(int energy) {
		Energy = energy;
	}

}