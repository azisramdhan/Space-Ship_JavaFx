/*******************************************************************
* File Name 	: Ship.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 29/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Use to create a space ship with canvas
*
********************************************************************/

//package view/class untuk membuat Interface
package view;

//import library yang dibutuhkan program
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

//class Ship diset publik agar bisa diakses oleh class Controller
public class Ship{

	/* Deklarasi atribut gobal*/

	//deklarasi atribut canvas dengan tipe data objek Canvas dengan permission type Public agar bisa diakses oleh class Controller
	public Canvas canvas;

	//deklarasi atribut x dan y : untuk menyimpan nilai koordinat Pesawat
	int x;
	int y;

	//deklarasi dan instansiasi atribut constant dengan tipe data objek class Constant : untuk mengatur size objek
	Constant constant = new Constant();


	/***************
	 * Konstruktor
	 ***************/
	public Ship(Group rootGame){
		//instansiasi atribut canvas
		canvas = new Canvas(constant.shipWidth,constant.shipHeight);
		//deklarasi dan Inisialisasi atribut gc dengan tipe data objek GraphicContext
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//memanggil method drawShip
		drawShip(gc);
		//memasukkan canvas yang telah di gambari pesawat ke dalam grup rootGame 
		rootGame.getChildren().add(canvas);
	}

	/***********************************************************************
	 * Method untuk menggambar pesawat menggunakan canvas, stroke dan paint
	 ***********************************************************************/
	private void drawShip(GraphicsContext gc) {

		//set lebar garis
		gc.setLineWidth(2);

		//deklarasi dan inisialisasi atribut array xpoints0 dan ypoints0 : untuk menyimpan nilai titik pada koordinat
		double xpoints0[] = {45, 15, 5,35,37.2,62,73.1,77.8,80.1,110.1,100.1,65.1};
		double ypoints0[] = {75, 80, 60,40,34,31,31,34,40,60,80,75};
		//mengganti warna stroke
		gc.setStroke(Paint.valueOf("#8ea8ad"));
		//mengganti warna shape
		gc.setFill(Paint.valueOf("#d3e1e7"));
		//menggambar sayap
		gc.fillPolygon(xpoints0, ypoints0, xpoints0.length);
		//menggambar garis sayap
		gc.strokePolygon(xpoints0, ypoints0, xpoints0.length);

		//mengganti tebal stroke
		gc.setLineWidth(3);
		//mengganti warna stroke
		gc.setStroke(Paint.valueOf("#bdd6db"));
		//mulai menggambar garis
		gc.beginPath();
		//koordinat awal garis
		gc.moveTo(41.5, 34.8);
		//koordinat titik selanjutnya dari garis
		gc.lineTo(28, 65);
		//koordinat titik selanjutnya (akhir) dari garis 
		gc.lineTo(38.5, 73.3);
		//menggambar bayangan sayap kanan
		gc.stroke();
		//mengakhiri menggambar garis
		gc.closePath();

		/* Selanjutnya digunakan fungsi-fungsi yang serupa yang ada di dalam graphic context, seperti setStroke, setFill, setPolygon (menggambar shape baik dengan ataupun tanpa fill colour) atau beginPath, moveTo,lineTo,stroke dan closePath (menggambar garis secara manual) dengan nilai koordinat yang berbeda */

		//menggambar bayangan sayap kiri
		gc.setStroke(Paint.valueOf("#bdd6db"));
		gc.beginPath();
		gc.moveTo(73.5, 34.8);
		gc.lineTo(87, 65);
		gc.lineTo(76, 73.3);
		gc.stroke();
		gc.closePath();

		//menggambar bayangan ujung sayap kanan
		gc.setLineWidth(4);
		gc.setStroke(Paint.valueOf("#bdd6db"));
		gc.beginPath();
		gc.moveTo(13.5, 59);
		gc.lineTo(17.5, 76.5);
		gc.stroke();
		gc.closePath();

		//menggambar bayangan ujung sayap kiri
		gc.setLineWidth(4);
		gc.setStroke(Paint.valueOf("#bdd6db"));
		gc.beginPath();
		gc.moveTo(101.1, 58);
		gc.lineTo(97.6, 76.5);
		gc.stroke();
		gc.closePath();

		//menggambar garis kuning di sayap kanan
		gc.setLineWidth(5);
		gc.setStroke(Paint.valueOf("#ffcc00"));
		gc.beginPath();
		gc.moveTo(20, 68);
		gc.lineTo(21.8, 75);
		gc.stroke();
		gc.closePath();

		//menggambar bayangan garis kuning sayap kanan
		gc.setLineWidth(2);
		gc.beginPath();
		gc.setStroke(Paint.valueOf("#bf9900"));
		gc.moveTo(21.8, 78.5);
		gc.lineTo(23.8, 78.2);
		gc.stroke();
		gc.closePath();

		//menggambar garis kuning di sayap kiri
		gc.setLineWidth(5);
		gc.setStroke(Paint.valueOf("#ffcc00"));
		gc.beginPath();
		gc.moveTo(94.6, 68);
		gc.lineTo(92.7, 75);
		gc.stroke();
		gc.closePath();

		//menggambar bayangan garis kuning sayap kiri
		gc.setLineWidth(2);
		gc.beginPath();
		gc.setStroke(Paint.valueOf("#bf9900"));
		gc.moveTo(90.6, 78.3);
		gc.lineTo(92.6, 78.6);
		gc.stroke();
		gc.closePath();

		//menggambar bayangan ujung sayap kiri
		gc.setLineWidth(4);
		gc.setStroke(Paint.valueOf("#bdd6db"));
		gc.beginPath();
		gc.moveTo(101.1, 58);
		gc.lineTo(97.6, 76.5);
		gc.stroke();
		gc.closePath();

		gc.setLineWidth(2);

		//menggambar ujung sayap kanan
		double xpoints1[] = {14, 0, 10,18};
		double ypoints1[] = {82.5, 57, 52,82};
		gc.setFill(Paint.valueOf("#36bbf5"));
		gc.fillPolygon(xpoints1, ypoints1, xpoints1.length);

		//menggambar garis ujung sayap kanan
		gc.setStroke(Paint.valueOf("#1784b4"));
		gc.beginPath();
		gc.moveTo(17.5, 81);
		gc.lineTo(14, 82.5);
		gc.lineTo(0, 57);
		gc.lineTo(11.5, 51);
		gc.lineTo(11.5, 54);
		gc.stroke();
		gc.closePath();

		//menggambar ujung sayap kiri
		double xpoints4[] = {101.1, 115.1, 105.1,97.1};
		double ypoints4[] = {82.5, 57, 52,82};
		gc.setFill(Paint.valueOf("#36bbf5"));
		gc.fillPolygon(xpoints4, ypoints4, xpoints4.length);

		//menggambar garis ujung sayap kiri
		gc.setStroke(Paint.valueOf("#1784b4"));
		gc.beginPath();
		gc.moveTo(97.6, 81);
		gc.lineTo(101.1, 82.5);
		gc.lineTo(115.1, 57);
		gc.lineTo(103.6, 51);
		gc.lineTo(103.6, 54);
		gc.stroke();
		gc.closePath();

		//menggambar bagian tengah pesawat (putih)
		gc.setLineWidth(2);
		double xpoints[] = {50, 30, 55,60, 85, 65};
		double ypoints[] = {80, 65, 0,0, 65, 80};
		gc.setStroke(Paint.valueOf("#cbcbcb"));
		gc.setFill(Paint.valueOf("#f2f2f2"));
        gc.fillPolygon(xpoints, ypoints, xpoints.length);
		gc.strokePolygon(xpoints, ypoints, xpoints.length);

		//menggambar bagian tengah pesawat (biru)
		double xpoints2[] = {53.5, 40, 56.5,58.5, 75, 61.5};
		double ypoints2[] = {80, 60, 0,0, 60, 80};
		gc.setFill(Paint.valueOf("#36bbf5"));
		gc.fillPolygon(xpoints2, ypoints2, xpoints2.length);

		//menggambar garis bagian tengah pesawat (biru)
		gc.setLineWidth(2);
		gc.setStroke(Paint.valueOf("#1884b4"));
		gc.beginPath();
		gc.moveTo(53.5,80);
		gc.lineTo(61.5, 80);
		gc.stroke();
		gc.closePath();
		gc.setStroke(Paint.valueOf("#8ad1f2"));
		gc.beginPath();
		gc.moveTo(56.5,30);
		gc.lineTo(58.5,30);
		gc.stroke();
		gc.closePath();

		//menggambar kaca pesawat
		double xpoints3[] = {50, 56, 59,65};
		double ypoints3[] = {50, 30, 30,50};
		gc.setFill(Paint.valueOf("#4a3c55"));
		gc.fillPolygon(xpoints3, ypoints3, xpoints3.length);
    }

    /********************************************************************
	 * Method untuk menggeser posisi/koordinat X pesawat di dalam scene
	 *******************************************************************/
    public void moveCanvasX(int x) {
		//inisialisasi atribut x dengan nilai dari parameter x
		this.x = x;
		//memanggil method setTranslateX pada class Canvas
		canvas.setTranslateX(x);
	}


    /********************************************************************
	 * Method untuk menggeser posisi/koordinat Y pesawat di dalam scene
	 *******************************************************************/
    public void moveCanvasY(int y) {
    	//inisialisasi atribut y dengan nilai dari parameter y
		this.y = y;
		//memanggil method setTranslateY pada class Canvas
		canvas.setTranslateY(y);
	}


    /***************************************************
	 * Method untuk mengambil nilai koordinat X pesawat
	 ***************************************************/
	public int getX(){
		//kembalikan nilai x
		return this.x;
	}

    /***************************************************
	 * Method untuk mengambil nilai koordinat Y pesawat
	 ***************************************************/
	public int getY(){
		//kembalikan nilai y
		return this.y;
	}

}