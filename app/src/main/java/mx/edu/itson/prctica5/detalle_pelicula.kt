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

            // Recibimos los asientos y actualizamos el texto como pide el PDF
            val seatsAvailable = bundle.getInt("numberSeats")
            tv_seats_left.text = "$seatsAvailable seats available"
        }

        btn_buy_tickets.setOnClickListener {
            val intent = Intent(this, SeatSelection::class.java)
            // Pasamos el título para mostrarlo en la siguiente pantalla
            intent.putExtra("titulo", tv_nombre_pelicula.text.toString())
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}