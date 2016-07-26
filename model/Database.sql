/*************************************************************************
* File Name 	: Game.java
* Programmer 	: Azis Ramdhan Darojat
* Date			: 12/05/2016
* Email			: azisramdhan92@gmail.com
* Website/Blog	: ramdhansan.blogspot.com
* Deskripsi		: Use to create a database for saving an username and 
*				  energy of player using some SQL Syntax
*
*************************************************************************/

#Create database FightTheChallenge
create database FightTheChallenge;
#Use database
use FightTheChallenge;

#Create table player
create table Player(
	#Use to save username of player
	username varchar(20) primary key,
	#Use to save energy of player
	energy int
);