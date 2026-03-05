package mx.edu.itson.prctica5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)

        val title: TextView = findViewById(R.id.titleSeats)
        var asientosDisponibles = 20

        var bundle = intent.extras
        if(bundle != null){
            title.text = bundle.getString("titulo") // Corregido para que lea el título de la película
            asientosDisponibles = intent.getIntExtra("asientosTotales", 20) // Recibe los asientos actuales
        }
        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

        val confirm: Button = findViewById(R.id.confirm)
        confirm.setOnClickListener{
            val confirm: Button = findViewById(R.id.confirm)
            confirm.setOnClickListener{

                var asientosAComprar = 0

                // Revisamos cuál fila tiene un botón seleccionado y leemos su texto (su número)
                if (row1.checkedRadioButtonId > -1) {
                    val rbSeleccionado = findViewById<RadioButton>(row1.checkedRadioButtonId)
                    asientosAComprar = rbSeleccionado.text.toString().toInt()
                } else if (row2.checkedRadioButtonId > -1) {
                    val rbSeleccionado = findViewById<RadioButton>(row2.checkedRadioButtonId)
                    asientosAComprar = rbSeleccionado.text.toString().toInt()
                } else if (row3.checkedRadioButtonId > -1) {
                    val rbSeleccionado = findViewById<RadioButton>(row3.checkedRadioButtonId)
                    asientosAComprar = rbSeleccionado.text.toString().toInt()
                } else if (row4.checkedRadioButtonId > -1) {
                    val rbSeleccionado = findViewById<RadioButton>(row4.checkedRadioButtonId)
                    asientosAComprar = rbSeleccionado.text.toString().toInt()
                }

                // Si encontró un asiento seleccionado (mayor a 0)
                if(asientosAComprar > 0) {

                    Toast.makeText(this, "Enjoy the movie", Toast.LENGTH_LONG).show()

                    // Restamos la cantidad exacta que decía el botón
                    asientosDisponibles -= asientosAComprar

                    // Le avisamos a la pantalla de detalle_pelicula el nuevo número
                    val resultIntent = Intent()
                    resultIntent.putExtra("asientosRestantes", asientosDisponibles)
                    setResult(RESULT_OK, resultIntent)

                    // Cerramos esta pantalla para volver a la anterior
                    finish()
                } else {
                    // Si le dio a Confirmar sin elegir nada
                    Toast.makeText(this, "Please select a seat first", Toast.LENGTH_SHORT).show()
                }
            }
        }


        row1.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId > -1){
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
            }
        }

        row2.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId > -1){
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()

                row2.check(checkedId)
            }
        }

        row3.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId > -1){
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()

                row3.check(checkedId)
            }
        }
        row4.setOnCheckedChangeListener { group, checkedId ->
            if(checkedId > -1){
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()

                row4.check(checkedId)
            }
        }





    }
}