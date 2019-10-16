package com.example.triquitrueque

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.CellIdentity
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.marginBottom
import androidx.core.view.setPadding
import kotlinx.android.synthetic.main.activity_practicar.*
import java.util.*
import kotlin.collections.ArrayList

class Practicar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practicar)
    }
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var posLibres = ArrayList<Int>()
    var posOcupadas = ArrayList<Int>()
    var cellActual : Int = 0
    var activePlayer = 1
    var lastPlayer = 2
    var player ="x"
    var fichasEnJuego = 1
    var anteriorCellID = 0
    var listoPaCambio = true  //TRUE: debo tocar ficha existente||||||| FALSE: debo poner la ficha tocada en casilla vacia
    var flag = false
    var winsPlayer1 = 0
    var winsPlayer2 = 0
    lateinit var  botonAnterior: Button

    fun buClick(view: View){
        val buSelected = view as Button

        var cellID = 0
        when(buSelected.id){
            R.id.bu1->cellID = 1
            R.id.bu2->cellID = 2
            R.id.bu3->cellID = 3
            R.id.bu4->cellID = 4
            R.id.bu5->cellID = 5
            R.id.bu6->cellID = 6
            R.id.bu7->cellID = 7
            R.id.bu8->cellID = 8
            R.id.bu9->cellID = 9
        }
        //Toast.makeText(this,"Cell ID: "+cellID,Toast.LENGTH_SHORT).show()
        playGame(cellID, buSelected)
    }

    private fun playGame(cellID: Int, buSelected: Button) {

        if(fichasEnJuego <= 6){
            if(activePlayer==1 ){

                //  buSelected.setTextColor(Color.parseColor("blue"))
                buSelected.text = "x"
                player1.add(cellID)
                activePlayer = 2
                fichasEnJuego = fichasEnJuego+1

            }else{

                buSelected.text = "O"
                player2.add(cellID)
                fichasEnJuego = fichasEnJuego+1
                activePlayer = 1
            }

            buSelected.isEnabled = false
            buSelected.setTextColor(Color.parseColor("black"))
            botonAnterior = buSelected
            posOcupadas.add(cellID)

            if (fichasEnJuego == 7){
                Toast.makeText(this,"Activar las X",Toast.LENGTH_SHORT).show()
                activarX()
            }

            //    checkWinner()
        }else {  //Se completo 6 fichas en el tablero ahora debo hacer el juego dinamicamente

            if (listoPaCambio) { //bandera necesaria para cambiar las fichas ya que llene un maxio

                activarBotonesLlenos()
                if (posOcupadas.contains(cellID) && (player == buSelected.text)) {   // PONGO EN NEGRO LA FICHA QUE VOY A  MOVER

                    buSelected.setBackgroundColor(Color.parseColor("black"))
                    buSelected.setTextColor(Color.parseColor("white"))
                    listoPaCambio = false
                    botonAnterior = buSelected   //en el futuro debo saber cual fue el boton que oprimi
                    anteriorCellID = cellID
                    desactivarBotonesLlenos()

                }
            } else {//SEGUNDA ETAPA: TENGO UNA FICHA AGARRADA, DEBO SOLTARLA EN UN LUGAR VACIO


                if (!posOcupadas.contains(cellID) && (player==botonAnterior.text)) {   //
                    lastPlayer = activePlayer
                    Toast.makeText(this,"hola si entre aca despues delr eset la cague",Toast.LENGTH_SHORT).show()
                    buSelected.setText(botonAnterior.text)
                    if(botonAnterior.text=="x"){
                        player="O"
                        player1.add(cellID)
                    }
                    if(botonAnterior.text=="O"){
                        player="x"
                        player2.add(cellID)
                    }
                    //  Toast.makeText(this,buSelected.text,Toast.LENGTH_LONG).show()

                    buSelected.setTextColor(Color.parseColor("black"))
                    listoPaCambio = true
                    botonAnterior.setBackgroundColor(Color.parseColor("white"))
                    botonAnterior.setText("")
                    //DEBO AGREGAR LA DINAMICA DE posOcupada
                    for (cellActual in posOcupadas) {   //Ciclo para eliminar la ficha que se movio
                        if (cellActual == anteriorCellID) {
                            // posOcupadas.remove(anteriorCellID)
                            posOcupadas.remove(anteriorCellID)
                            if(player=="O"){
                                player1.remove(anteriorCellID)
                            }else{
                                player2.remove(anteriorCellID)
                            }
                            break
                        }
                    }
                    posOcupadas.add(cellID)
                    activarBotonesLlenos()
                    buSelected.isEnabled = true
                    botonAnterior.isEnabled = true
                }
            }

        }
        checkWinner()

    }

    private fun desactivarBotonesLlenos() {
        if (!posOcupadas.contains(1)) {
            bu1.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu1.isEnabled = false
        }
        if (!posOcupadas.contains(2)) {
            bu2.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu2.isEnabled = false
        }
        if (!posOcupadas.contains(3)) {
            bu3.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu3.isEnabled = false
        }
        if (!posOcupadas.contains(4)) {
            bu4.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu4.isEnabled = false
        }
        if (!posOcupadas.contains(5)) {
            bu5.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu5.isEnabled = false
        }
        if (!posOcupadas.contains(6)) {
            bu6.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu6.isEnabled = false
        }
        if (!posOcupadas.contains(7)) {
            bu7.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu7.isEnabled = false
        }
        if (!posOcupadas.contains(8)) {
            bu8.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu8.isEnabled = false
        }
        if (!posOcupadas.contains(9)) {
            bu9.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu9.isEnabled = false
        }
    }

    private fun activarBotonesLlenos() {
        if (posOcupadas.contains(1)) {
            bu1.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu1.isEnabled = false
        }
        if (posOcupadas.contains(2)) {
            bu2.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu2.isEnabled = false
        }
        if (posOcupadas.contains(3)) {
            bu3.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu3.isEnabled = false
        }
        if (posOcupadas.contains(4)) {
            bu4.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu4.isEnabled = false
        }
        if (posOcupadas.contains(5)) {
            bu5.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu5.isEnabled = false
        }
        if (posOcupadas.contains(6)) {
            bu6.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu6.isEnabled = false
        }
        if (posOcupadas.contains(7)) {
            bu7.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu7.isEnabled = false
        }
        if (posOcupadas.contains(8)) {
            bu8.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu8.isEnabled = false
        }
        if (posOcupadas.contains(9)) {
            bu9.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu9.isEnabled = false
        }

    }

    private fun checkWinner() {
        var winner = -1
        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }
        //row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }
        //row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }
        //columna 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }
        //columna 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }
        //columna 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }
        //diagonal negativa
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }
        //diagonal positiva
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }

        if(winner !=-1){
            if(winner==1){
                winsPlayer1 = winsPlayer1+1
                etPlayer1.setText(winsPlayer1.toString())
                Toast.makeText(this,"Jugador 1 Gano",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Jugador 2 Gano",Toast.LENGTH_LONG).show()
                winsPlayer2 = winsPlayer2+1
                etPlayer2.setText(winsPlayer2.toString())
            }
            reset()
        }

    }
    private fun activarX(){
        if (bu1.text== "x") {
            bu1.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu1.isEnabled = false
        }
        if (bu2.text== "x") {
            bu2.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu2.isEnabled = false
        }
        if (bu3.text== "x") {
            bu3.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu3.isEnabled = false
        }
        if (bu4.text== "x") {
            bu4.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu4.isEnabled = false
        }
        if (bu5.text== "x") {
            bu5.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu5.isEnabled = false
        }
        if (bu6.text== "x") {
            bu6.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu6.isEnabled = false
        }
        if (bu7.text== "x") {
            bu7.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu7.isEnabled = false
        }
        if (bu8.text== "x") {
            bu8.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu8.isEnabled = false
        }
        if (bu9.text== "x") {
            bu9.isEnabled = true
            flag =
                false   //ME FALTA LA CONDICION PARA PISAR FICHA ACTIVADA Y HABILITAR CELDAS VACIAS
        } else {
            bu9.isEnabled = false
        }

    }

    private fun reset() {
        bu1.isEnabled = true
        bu2.isEnabled = true
        bu3.isEnabled = true
        bu4.isEnabled = true
        bu5.isEnabled = true
        bu6.isEnabled = true
        bu7.isEnabled = true
        bu8.isEnabled = true
        bu9.isEnabled = true
        player1.clear()
        player2.clear()
        posLibres.clear()
        posOcupadas.clear()
        cellActual = 0
        activePlayer = 1
        lastPlayer = 2
        player="x"
        fichasEnJuego=1
        anteriorCellID=0
        listoPaCambio=true
        flag = false
        bu1.text = ""
        bu2.text = ""
        bu3.text = ""
        bu4.text = ""
        bu5.text = ""
        bu6.text = ""
        bu7.text = ""
        bu8.text = ""
        bu9.text = ""


    }

    private fun autoPlay(){
        var emptyCells = ArrayList<Int>()
        for (cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        val randIndex = Random().nextInt(emptyCells.size-0)+0
        val cellID = emptyCells[randIndex]
        var buSelected: Button = bu1

        when(cellID){
            1->buSelected = bu1
            2->buSelected = bu2
            3->buSelected = bu3
            4->buSelected = bu4
            5->buSelected = bu5
            6->buSelected = bu6
            7->buSelected = bu7
            8->buSelected = bu8
            9->buSelected = bu9

        }
        playGame(cellID,buSelected)
    }
}
