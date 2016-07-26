/*******************************************************************
* File Name 	: Meteor.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 31/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Use to create a meteor with canvas
*
********************************************************************/

//package view/class untuk membuat Interface
package view;

//import package controller
import controller.*;

//import library yang dibutuhkan program
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

//class Meteor diset publik agar bisa diakses oleh class Controller
public class Meteor{

	/* Deklarasi atribut gobal*/

	//deklarasi atribut canvas dengan tipe data objek Canvas dengan permission type Public agar bisa diakses oleh class Controller
	public Canvas canvas;

	//deklarasi atribut gc dengan tipe data objek GraphicsContext
	GraphicsContext gc;
	//deklarasi atribut rootGame dengan tipe data objek Group
	Group rootGame;

	//deklarasi dan inisialisasi atribut status dengan nilai awal false
	Boolean status = false;

	//deklarasi atribut x dan y : untuk menyimpan nilai koordinat Pesawat
	double x = 0;
	double y = 0;

	//deklarasi dan instansiasi atribut constant dengan tipe data objek class Constant : untuk mengatur size objek
	Constant constant = new Constant();

	/***************
	 * Konstruktor
	 ***************/
	public Meteor(Group rootGame){
		//inisialisasi atribut rootGame dengan nilai dari parameter
		this.rootGame = rootGame;
		//instansiasi atribut canvas
		canvas = new Canvas(constant.meteorWidth,constant.meteorHeight);
		//Inisialisasi atribut gc dengan tipe data objek GraphicContext
		gc = canvas.getGraphicsContext2D();
		//memanggil method drawMeteor
		drawMeteor(gc);
		//memasukkan canvas yang telah di gambari meteor ke dalam grup rootGame
		rootGame.getChildren().add(canvas);
	}

	/***********************************************************************
	 * Method untuk menggambar meteor menggunakan canvas, stroke dan paint
	 ***********************************************************************/
	private void drawMeteor(GraphicsContext gc){
		
		//set tebal garis
		gc.setLineWidth(2);
		//set warna garis
		gc.setStroke(Paint.valueOf("#5b4333"));
		//set warna fill
		gc.setFill(Paint.valueOf("#836049"));
		//temp variabel
		double xpoints[] = {0, 10, 55, 70, 60, 45, 20};
		double ypoints[] = {35, 0, 5, 25, 50, 50, 60};
		//menggambar fill meteor
		gc.fillPolygon(xpoints,ypoints,xpoints.length);
		//menggambar stroke meteor
		gc.strokePolygon(xpoints,ypoints,xpoints.length);

		//set warna fill bagian dalam meteor
		gc.setFill(Paint.valueOf("#997055"));
		//temp variabel
		double xpoints2[] = {10, 20, 45, 60, 50, 45, 30};
		double ypoints2[] = {35, 10, 11, 25, 40, 40, 50};
		//menggambar fill bagian dalam meteor
		gc.fillPolygon(xpoints2,ypoints2,xpoints2.length);
		
	}



    /********************************************************************
	 * Method untuk menggeser posisi/koordinat X meteor di dalam scene
	 *******************************************************************/
	public void setX(double x){
		//inisialisasi atribut x dengan nilai dari parameter x
		this.x = x;
		//memanggil method setTranslateX pada class Canvas
		canvas.setTranslateX(x);
	}

    /********************************************************************
	 * Method untuk menggeser posisi/koordinat Y meteor di dalam scene
	 *******************************************************************/	
	public void setY(double y){
		//inisialisasi atribut y dengan nilai dari parameter y
		this.y = y;
		//memanggil method setTranslateY pada class Canvas
		canvas.setTranslateY(y);
	}

    /***************************************************
	 * Method untuk mengambil nilai koordinat X meteor
	 ***************************************************/
	public double getX(){
		//kembalikan nilai x
		return this.x;
	}

    /***************************************************
	 * Method untuk mengambil nilai koordinat Y meteor
	 ***************************************************/
	public double getY(){
		//kembalikan nilai y
		return this.y;
	}

    /**********************************************
	 * Method untuk menghapus gambar pada canvas
	 **********************************************/	
	public void cleanCanvas(){
		// -- System.out.println("ini y : " + getY());
		// -- System.out.println("ini x : " + getX());
		// -- System.out.println("ini w : " + canvas.getWidth());
		// -- System.out.println("ini h : " + canvas.getHeight());
		rootGame.getChildren().remove(canvas);
	}

    /*******************************************************
	 * Method untuk men set status meteor (terlewati/tidak)
	 *******************************************************/
	public void setStatus(GameController controller){
		if(!status){
			status = true;
			//tambah energy disini
			controller.incEnergyValue();
			//print true jika terlewati (Debugging)
			// -- System.out.println(status);
		}
	}

}