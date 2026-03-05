package mx.edu.itson.prctica5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageView
import android.widget.TextView


class detalle_pelicula : AppCompatActivity() {
    var seatsAvailable = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)

        val iv_pelicula_imagen: ImageView = findViewById(R.id.iv_pelicula_imagen)
        val tv_nombre_pelicula: TextView = findViewById(R.id.tv_nombre_pelicula)
        val tv_nombre_desc: TextView = findViewById(R.id.tv_nombre_desc)
        val tv_seats_left: TextView = findViewById(R.id.seatLeft)
        val btn_buy_tickets: Button = findViewById(R.id.buyTickets)


        val bundle = intent.extras
        if(bundle != null){
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre_pelicula.setText(bundle.getString("titulo"))
            tv_nombre_desc.setText(bundle.getString("sinopsis"))


            seatsAvailable = intent.getIntExtra("numeberSeats", 20)
            tv_seats_left.text = "$seatsAvailable seats available"
        }

        btn_buy_tickets.setOnClickListener {
            val intent = Intent(this, SeatSelection::class.java)
            intent.putExtra("titulo", tv_nombre_pelicula.text.toString())
            intent.putExtra("asientosTotales", seatsAvailable) // Le pasamos a los asientos cuántos quedan

            // Usamos startActivityForResult para "esperar" a que regrese la nueva cantidad
            startActivityForResult(intent, 100)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            if (data != null) {
                // Sacamos la nueva cantidad de asientos y actualizamos el texto
                seatsAvailable = data.getIntExtra("asientosRestantes", seatsAvailable)
                val tv_seats_left: TextView = findViewById(R.id.seatLeft)
                tv_seats_left.text = "$seatsAvailable seats available"
            }
        }
    }
}