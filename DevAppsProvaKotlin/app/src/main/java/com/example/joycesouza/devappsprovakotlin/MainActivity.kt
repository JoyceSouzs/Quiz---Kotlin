package com.example.joycesouza.devappsprovakotlin

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_blank.*

class MainActivity : AppCompatActivity() {

    var adapter: AdapterParticipante? = null
    var resultado = ArrayList<Participante>()
    var part: Participante = Participante()
    val manager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val trans = manager.beginTransaction()
        val fragment = BlankFragment()
        trans.replace(R.id.fragmentS,fragment)
        trans.addToBackStack(null)
        trans.commit()

        //Abrindo a outra tela e retornando dados
        btInciarQuiz.setOnClickListener {
            val iniciarQuizIntent = Intent(this, QuizActivity::class.java)
            startActivityForResult(iniciarQuizIntent, CODE_ACTIVITY_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODE_ACTIVITY_REQUEST && data != null) {

                //Pegando as informações da tela QuizActivity e populando o arraylist exibindo na listView
                var nomeOutraActivity = data.getStringExtra(NOME_ACTIVITY_REQUEST)
                var pontuacaoOutraActivity = data.getStringExtra(PONTUACAO_ACTIVITY_REQUEST)

                part.mNome = nomeOutraActivity
                part.mPontuacao = pontuacaoOutraActivity

                resultado.add(part)

                adapter = AdapterParticipante(this, resultado)
                listPessoa.adapter = adapter


            }
        }
    }

    companion object {
       private val CODE_ACTIVITY_REQUEST = 1
       val NOME_ACTIVITY_REQUEST = "proxima_activity_name"
        val PONTUACAO_ACTIVITY_REQUEST = "proxima_activity_pontuacao"
    }

}
