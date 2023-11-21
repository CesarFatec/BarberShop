package com.stackmobile.barbershop.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.stackmobile.barbershop.MainActivity
import com.stackmobile.barbershop.databinding.ActivityConsultarBinding

class Consultar : AppCompatActivity() {
    
    lateinit var binding: ActivityConsultarBinding
    private val db = FirebaseFirestore.getInstance()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btFinalizar.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val voltarTelaLogin = Intent(this,MainActivity::class.java)
            startActivity(voltarTelaLogin)
            finish()
        }
        binding.btAtualizar.setOnClickListener {
            db.collection("Agendamento").document("Beca")
                .update("Cliente","Danielle").addOnCompleteListener {
                    Log.d("db_update", "Agenda atualizada com sucesso!!!")
                }
        }

        binding.btConsultar.setOnClickListener {
            db.collection("Agendamento").document("Beca")
                .addSnapshotListener { documento, error ->
                    if (documento != null){
                        binding.tvCliente.text = documento.getString("Cliente")
                        binding.tvBarbeiro.text = documento.getString("Barbeiro")
                        binding.tvData.text = documento.getString("Data")
                        binding.tvHora.text = documento.getString("Hora")

                    }
        binding.btExcluir.setOnClickListener {
            db.collection("Agendamento").document("Debora")
                .delete().addOnCompleteListener {
                    Log.d("db_delete","Agendamento excluído com sucesso!!!")
                }
        }
        }
        }
        }

    }
