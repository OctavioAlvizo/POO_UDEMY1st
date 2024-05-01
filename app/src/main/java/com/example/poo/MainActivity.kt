package com.example.poo

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.poo.ui.theme.POOTheme

class MainActivity : ComponentActivity() {
    companion object{
        lateinit var maincontext: Context
    }
    private lateinit var pok: Pokemon
    private lateinit var waterPok: Waterpokemon
    private lateinit var firePok: Firepokemon
    private lateinit var earthPok: Earthpokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        maincontext = this

        var num: Int = 0
        var Octi: Person = Person(nombre = "Octavio", passport = "OAHA12345")
        var Ansel: Person = Person()
        println(Octi.alive)
        println(Octi.nombre)
        println(Octi.passport)

        Ansel.Person()
        Ansel.nombre = "Tavolone"
        println(Ansel.alive)
        println(Ansel.nombre)
        println(Ansel.passport)

        Octi.die()
        println(Octi.alive)

        var pele: Athlete = Athlete("Pele", "qweasd654", "Futbol")

        println(pele.alive)
        println(pele.nombre)
        println(pele.passport)
        println(pele.sport)

        pele.die()
        println(pele.alive)

        var bicho : Pokemon = Pokemon()
        println(bicho.getName())
        println(bicho.getAttackPower())
        bicho.setLife(60f)
        println(bicho.getLife())

        var btFight = findViewById<Button>(R.id.btFight)
        btFight.setOnClickListener{
            fight(firePok, earthPok)
        }
    }
    private fun fight(p1: Pokemon, p2: Pokemon) {

        var emtLog = findViewById<EditText>(R.id.emtLog)
        emtLog.setText("")
        var text = ""

        text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs ${p2.getName()} (${p2.getLife().toInt()})"

        while (p1.getLife() > 0 && p2.getLife() > 0){
            text += "\n${p1.getName()} ataca!"
            p1.attack();
            p2.setLife(p2.getLife() - p1.getAttackPower())
            text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs ${p2.getName()} (${p2.getLife().toInt()})"
            if (p2.getLife() > 0){
                text += "\n${p2.getName()} ataca!"
                p2.attack()
                p1.setLife(p1.getLife() - p2.getAttackPower())
                text += "\n${p1.getName()} (${p1.getLife().toInt()}) Vs ${p2.getName()} (${p2.getLife().toInt()})"
            }
        }

        if (p1.getLife() > 0) text += "\n\nEL CAMPEON ES ${p1.getName()}"
        else text += "\n\nEL CAMPEON ES ${p2.getName()}"

        emtLog.setText(text)


        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)

        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }

    fun loadDataPokemon(tv: TextView, p: Pokemon){
        var description: String = ""

        description += p.getName() + " ("
        description += "AP: " + p.getAttackPower().toInt()
        description += " - L: " + p.getLife().toInt() + ")"

        tv.text = description
    }

    fun createNewPokemon(v: View){
        var etName = findViewById<EditText>(R.id.etName)
        var etAttackPower = findViewById<EditText>(R.id.etAttackPower)

        pok = Pokemon()

        if (!etName.text.isNullOrEmpty() && !etAttackPower.text.isNullOrEmpty())
            pok.Pokemon(etName.text.toString(), etAttackPower.text.toString().toFloat())

        var ivPokemon = findViewById<ImageView>(R.id.ivPokemon)
        ivPokemon.setImageResource(R.mipmap.pokemon)

        var tvPokemon = findViewById<TextView>(R.id.tvPokemon)
        loadDataPokemon(tvPokemon, pok)
    }
    fun createNewWaterPokemon(v: View){
        var etWaterName = findViewById<EditText>(R.id.etWaterName)
        var etWaterAttackPower = findViewById<EditText>(R.id.etWaterAttackPower)
        var etWaterMaxResistence = findViewById<EditText>(R.id.etWaterMaxResistence)

        waterPok = Waterpokemon()

        if (!etWaterName.text.isNullOrEmpty() && !etWaterAttackPower.text.isNullOrEmpty())
            waterPok.Waterpokemon(etWaterName.text.toString(),
                etWaterAttackPower.text.toString().toFloat(),
                etWaterMaxResistence.text.toString().toInt())

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water)
        ivWaterPokemon.setBackgroundColor(ContextCompat.getColor(this,R.color.white))


        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPok)
    }
    fun cureWaterPokemon(v: View){
        waterPok.cure()
        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPok)
    }
    fun sayHiWaterPokemon(v: View){ waterPok.sayHi() }
    fun evolveWaterPokemon(v: View){

        var etEvolveWaterPokemon = findViewById<EditText>(R.id.etEvolveWaterPokemon)

        waterPok.evolve(etEvolveWaterPokemon.text.toString())

        var ivWaterPokemon = findViewById<ImageView>(R.id.ivWaterPokemon)
        ivWaterPokemon.setImageResource(R.mipmap.water_evolved)

        var tvWaterPokemon = findViewById<TextView>(R.id.tvWaterPokemon)
        loadDataPokemon(tvWaterPokemon, waterPok)
    }

    fun createNewFirePokemon(v: View){
        var etFireName = findViewById<EditText>(R.id.etFireName)
        var etFireAttackPower = findViewById<EditText>(R.id.etFireAttackPower)
        var etFireBallTemperature = findViewById<EditText>(R.id.etFireBallTemperature)

        firePok = Firepokemon()

        if (!etFireName.text.isNullOrEmpty() && !etFireAttackPower.text.isNullOrEmpty())
            firePok.Firepokemon(etFireName.text.toString(),
                etFireAttackPower.text.toString().toFloat(),
                etFireBallTemperature.text.toString().toInt())

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire)
        ivFirePokemon.setBackgroundColor(ContextCompat.getColor(this,R.color.white))


        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)
    }
    fun cureFirePokemon(v: View){
        firePok.cure()
        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)
    }
    fun sayHiFirePokemon(v: View){ firePok.sayHi() }
    fun evolveFirePokemon(v: View){

        var etEvolveFirePokemon = findViewById<EditText>(R.id.etEvolveFirePokemon)

        firePok.evolve(etEvolveFirePokemon.text.toString())

        var ivFirePokemon = findViewById<ImageView>(R.id.ivFirePokemon)
        ivFirePokemon.setImageResource(R.mipmap.fire_evolved)

        var tvFirePokemon = findViewById<TextView>(R.id.tvFirePokemon)
        loadDataPokemon(tvFirePokemon, firePok)
    }



    fun createNewEarthPokemon(v: View){
        var etEarthName = findViewById<EditText>(R.id.etEarthName)
        var etEarthAttackPower = findViewById<EditText>(R.id.etEarthAttackPower)
        var etEarthMaxDepth = findViewById<EditText>(R.id.etEarthMaxDepth)

        earthPok = Earthpokemon()

        if (!etEarthName.text.isNullOrEmpty() && !etEarthAttackPower.text.isNullOrEmpty())
            earthPok.Earthpokemon(etEarthName.text.toString(),
                etEarthAttackPower.text.toString().toFloat(),
                etEarthMaxDepth.text.toString().toInt())

        var ivEarthPokemon = findViewById<ImageView>(R.id.ivEarthPokemon)
        ivEarthPokemon.setImageResource(R.mipmap.earth)
        ivEarthPokemon.setBackgroundColor(ContextCompat.getColor(this,R.color.white))


        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }
    fun cureEarthPokemon(v: View){
        earthPok.cure()
        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }
    fun sayHiEarthPokemon(v: View){ earthPok.sayHi() }
    fun evolveEarthPokemon(v: View){

        var etEvolveEarthPokemon = findViewById<EditText>(R.id.etEvolveEarthPokemon)

        earthPok.evolve(etEvolveEarthPokemon.text.toString())

        var ivEarthPokemon = findViewById<ImageView>(R.id.ivEarthPokemon)
        ivEarthPokemon.setImageResource(R.mipmap.earth_evolved)

        var tvEarthPokemon = findViewById<TextView>(R.id.tvEarthPokemon)
        loadDataPokemon(tvEarthPokemon, earthPok)
    }

    fun sayByeEarthPokemon(v: View){
        earthPok.sayBye()
    }



}
