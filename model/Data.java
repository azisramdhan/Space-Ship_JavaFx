/*******************************************************************
* File Name     : Data.java
* Programmer    : Azis Ramdhan Darojat
* Date          : 31/05/2016
* Email         : azisramdhan92@gmail.com
* Website/Blog  : ramdhansan.blogspot.com
* Deskripsi     : Use to get from, update or insert data to database
*
********************************************************************/

//package model/class yang digunakan untuk mengakses database 
package model;

//class Data
public class Data{

	/* Deklarasi atribut global untuk menyimpan nilai dari database */
	int[] intEnergy = new int[30];
	String[] energy = new String[30];
	String[] username = new String[30];
	int record;
	

    /************************************************************************************************
     * Method ini digunakan untuk mengambil nilai dari database dan menyimpan ke dalam atribut gobal
     ************************************************************************************************/	
	public void fetch(){

		try{
			//deklarasi dan instansiasi atribut en dengan tipe data objek class Energy
			Energy en =  new Energy();
			//memanggil method getAll data pada class Energy
			en.getAllData();
			//inisialisasi atribut record
			record = 0;

			//perulangan selama masih ada data selanjutnya
			while(en.getResult().next()){

				/* Menyimpan data yang diambil dari database ke dalam atribut global */

				username[record] = en.getResult().getString(1);
				energy[record] = en.getResult().getString(2);
				intEnergy[record] = Integer.parseInt(energy[record]);
				
				//increment atribut record
				record++;
			}
			//memanggil method closeResult pada class Energy
			en.closeResult();
			////memanggil method closeConnection pada class Energy
			en.closeConnection();
		}catch(Exception e){
			e.toString();
		}
	
	}

    /****************************************************************************
     * Method ini digunakan untuk insert username dan energy untuk pemain baru
     ****************************************************************************/	
	public void insert(String username){
		try{
			//deklarasi dan instansiasi atribut en dengan tipe data objek class Energy
			Energy en =  new Energy();
			//memanggil method insertData pada class Energy
			en.insertData(username);
		}catch(Exception e){
			e.toString();
		}
	}

    /**************************************************
     * Method ini digunakan untuk update nilai energy
     **************************************************/	
	public void update(int energy, String username){
		try{
			//deklarasi dan instansiasi atribut en dengan tipe data objek class Energy
			Energy en =  new Energy();
			//memanggil method updateData pada class Energy
			en.updateData(energy, username);
		}catch(Exception e){
			e.toString();
		}
	}

    /**************************************************************************************************
     * Method ini digunakan untuk mengecek apakah data yang di input sudah ada di database/tidak
     **************************************************************************************************/	
	public boolean check(String username){
		//deklarasi dan instansiasi atribut isPlayerOld : untuk pengecekan apakah pemain baru atau bukan
		Boolean isPlayerOld = false;
		for(int i=0;i<record;i++){
			if(this.username[i].equals(username)){
				//inisialisasi atribut isPlayerOld dengan nilai true
				isPlayerOld = true;
			}
		}
		//kembalikan nilai negasi dari isPlayerOld
		return !isPlayerOld;

	}

	/* Getter atribut global */

	public int getRecord(){
		return this.record;
	}

	public String[] getUsername(){
		return this.username;
	}

	public String[] getEnergy(){
		return this.energy;
	}

}