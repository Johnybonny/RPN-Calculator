package com.example.kalkulator

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.ArrayDeque
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt


class MainActivity : AppCompatActivity() {
    private val requestCode = 44
    private var stack = ArrayDeque<Float>()
    private var previous = ArrayDeque<Float>()
    private var tmp = String()
    private var previoustmp = String()
    private var precision = 2
    private var arrow = "==>"


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonEnter: Button = findViewById(R.id.enter)
        val buttonUndo: Button = findViewById(R.id.undo)
        val buttonClear: Button = findViewById(R.id.clear)
        val button0: Button = findViewById(R.id.key0)
        val button1: Button = findViewById(R.id.key1)
        val button2: Button = findViewById(R.id.key2)
        val button3: Button = findViewById(R.id.key3)
        val button4: Button = findViewById(R.id.key4)
        val button5: Button = findViewById(R.id.key5)
        val button6: Button = findViewById(R.id.key6)
        val button7: Button = findViewById(R.id.key7)
        val button8: Button = findViewById(R.id.key8)
        val button9: Button = findViewById(R.id.key9)
        val buttonDot: Button = findViewById(R.id.dot)
        val buttonDrop: Button = findViewById(R.id.drop)
        val buttonSign: Button = findViewById(R.id.sign)
        val buttonRoot: Button = findViewById(R.id.root)
        val buttonSwap: Button = findViewById(R.id.swap)
        val buttonPlus: Button = findViewById(R.id.plus)
        val buttonMinus: Button = findViewById(R.id.minus)
        val buttonDivide: Button = findViewById(R.id.divide)
        val buttonMultiply: Button = findViewById(R.id.multiply)
        val buttonPower: Button = findViewById(R.id.power)

        val textStack1: TextView = findViewById(R.id.textStack1)
        val textStack2: TextView = findViewById(R.id.textStack2)
        val textStack3: TextView = findViewById(R.id.textStack3)
        val textStack4: TextView = findViewById(R.id.textStack4)
        val textTmp: TextView = findViewById(R.id.textTmp)


        fun roundNumber(number: Float, prec: Int): Float {
            var divider = 1
            for(i in 1..prec){
                divider *= 10
            }
            return round(number * divider) / divider
        }


        fun display(){
            val lista = stack.toArray()
            textTmp.text = tmp
            if(tmp != ""){
                textStack1.text = arrow
                when(stack.size){
                    0 ->{
                        textStack2.text = "1: "
                        textStack3.text = "2: "
                        textStack4.text = "3: "
                    }
                    1 -> {
                        textStack2.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack3.text = "2: "
                        textStack4.text = "3: "
                    }
                    2 -> {
                        textStack2.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack3.text = "2: ".plus(lista[1].toString())
                        textStack4.text = "3: "
                    }
                    else -> {
                        textStack2.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack3.text = "2: ".plus(roundNumber(lista[1] as Float, precision).toString())
                        textStack4.text = "3: ".plus(roundNumber(lista[2] as Float, precision).toString())
                    }
                }
            } else {
                when(stack.size){
                    0 ->{
                        textStack1.text = "1: "
                        textStack2.text = "2: "
                        textStack3.text = "3: "
                        textStack4.text = "4: "
                    }
                    1 -> {
                        textStack1.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack2.text = "2: "
                        textStack3.text = "3: "
                        textStack4.text = "4: "
                    }
                    2 -> {
                        textStack1.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack2.text = "2: ".plus(roundNumber(lista[1] as Float, precision).toString())
                        textStack3.text = "3: "
                        textStack4.text = "4: "
                    }
                    3 -> {
                        textStack1.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack2.text = "2: ".plus(roundNumber(lista[1] as Float, precision).toString())
                        textStack3.text = "3: ".plus(roundNumber(lista[2] as Float, precision).toString())
                        textStack4.text = "4: "
                    }
                    else -> {
                        textStack1.text = "1: ".plus(roundNumber(lista[0] as Float, precision).toString())
                        textStack2.text = "2: ".plus(roundNumber(lista[1] as Float, precision).toString())
                        textStack3.text = "3: ".plus(roundNumber(lista[2] as Float, precision).toString())
                        textStack4.text = "4: ".plus(roundNumber(lista[3] as Float, precision).toString())
                    }
                }
            }
        }
        display()

        fun numericalKey(number: String){
            previoustmp = tmp
            tmp += number
            display()
        }

        fun operationKeyDouble(operation: String){
            if(tmp.isNotEmpty()){
                Toast.makeText(this, "Add the edited element on the stack first", Toast.LENGTH_SHORT).show()
            } else {
                if(stack.size < 2){
                    Toast.makeText(this, "Insufficient number of elements", Toast.LENGTH_SHORT).show()
                } else {
                    previous = stack.clone()
                    val a = stack.pop()
                    val b = stack.pop()
                    when(operation){
                        "+" -> {
                            stack.push(b + a)
                        }
                        "-" -> {
                            stack.push(b - a)
                        }
                        "x" -> {
                            stack.push(b * a)
                        }
                        "/" -> {
                            if (a == 0.toFloat()) {
                                Toast.makeText(this, "You can't divide by 0", Toast.LENGTH_SHORT).show()
                                stack.push(b)
                                stack.push(a)
                            } else {
                                stack.push(b / a)
                            }
                        }
                        "power" -> {
                            stack.push(b.pow(a))
                        }
                        "swap" -> {
                            stack.push(a)
                            stack.push(b)
                        }
                    }
                }
            }
            display()
        }

        fun operationKeySingle(operation: String){
            if(tmp.isNotEmpty()){
                Toast.makeText(this, "Add the edited element on the stack first", Toast.LENGTH_SHORT).show()
            } else {
                if(stack.size < 1){
                    Toast.makeText(this, "Insufficient number of elements", Toast.LENGTH_SHORT).show()
                } else {
                    previous = stack.clone()
                    val a = stack.pop()
                    when(operation){
                        "root" -> {
                            if(a < 0.toFloat()){
                                Toast.makeText(this, "You can't compute the square root of a negative number", Toast.LENGTH_SHORT).show()
                                stack.push(a)
                            } else {
                                stack.push(sqrt(a))
                            }
                        }
                        "sign" -> {
                            stack.push(-1*a)
                        }
                        "drop" -> {
                        }
                    }
                }
            }
            display()
        }

        fun pressEnter(){
            if(tmp.isNotEmpty()){
                if((tmp.length - tmp.replace(".", "").length ) > 1){
                    Toast.makeText(this, "Too much dots", Toast.LENGTH_SHORT).show()
                } else {
                    if(tmp == "."){
                        tmp = "0"
                    }
                    previous = stack.clone()
                    stack.push(tmp.toFloat())
                }
            } else {
                if(stack.isEmpty()){
                    Toast.makeText(this, "You should enter a number first", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Duplicated the first element", Toast.LENGTH_SHORT).show()
                    previous = stack.clone()
                    val a = stack.pop()
                    stack.push(a)
                    stack.push(a)
                }
            }
            tmp = String()
            previoustmp = String()
            display()
        }

        fun pressClear(){
            previous = stack.clone()
            stack.clear()
            display()
        }

        fun pressUndo(){
            if(previoustmp.isEmpty()){
                if(stack.toString() == previous.toString()){
                    Toast.makeText(this, "You can only undo one move", Toast.LENGTH_SHORT).show()
                } else {
                    stack = previous.clone()
                }
            } else {
                if(tmp == previoustmp){
                    Toast.makeText(this, "You can only undo one move", Toast.LENGTH_SHORT).show()
                } else {
                    tmp = previoustmp
                }
            }
            display()
        }


        buttonEnter.setOnClickListener{ pressEnter() }
        buttonUndo.setOnClickListener{ pressUndo() }
        buttonClear.setOnClickListener{ pressClear() }
        button0.setOnClickListener{ numericalKey("0") }
        button1.setOnClickListener{ numericalKey("1") }
        button2.setOnClickListener{ numericalKey("2") }
        button3.setOnClickListener{ numericalKey("3") }
        button4.setOnClickListener{ numericalKey("4") }
        button5.setOnClickListener{ numericalKey("5") }
        button6.setOnClickListener{ numericalKey("6") }
        button7.setOnClickListener{ numericalKey("7") }
        button8.setOnClickListener{ numericalKey("8") }
        button9.setOnClickListener{ numericalKey("9") }
        buttonDot.setOnClickListener{ numericalKey(".") }
        buttonDrop.setOnClickListener{ operationKeySingle("drop") }
        buttonSign.setOnClickListener{ operationKeySingle("sign") }
        buttonRoot.setOnClickListener{ operationKeySingle("root") }
        buttonSwap.setOnClickListener{ operationKeyDouble("swap") }
        buttonPlus.setOnClickListener{ operationKeyDouble("+") }
        buttonMinus.setOnClickListener{ operationKeyDouble("-") }
        buttonDivide.setOnClickListener{ operationKeyDouble("/") }
        buttonMultiply.setOnClickListener{ operationKeyDouble("x") }
        buttonPower.setOnClickListener{ operationKeyDouble("power") }
    }


    fun settingsClicked(view: View) {
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra("precision", precision)
        intent.putExtra("arrow", arrow)
        startActivityForResult(intent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null){
            if(resultCode == Activity.RESULT_OK){
                val p = data.getIntExtra("returnPrecision", 2)
                val s = data.getStringExtra("returnArrow")
                precision = p
                arrow = s.toString()
            } else {
                Toast.makeText(this, "There was an error", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "To submit changes leave settings using special button", Toast.LENGTH_SHORT).show()
        }

    }

}

