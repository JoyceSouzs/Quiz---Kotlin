package com.example.joycesouza.devappsprovakotlin

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.joycesouza.devappsprovakotlin.R.string.participante
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private var pontos: Int = 0
    private var nomeMusica: String? = null
    private var participante: String? = null
    private var opcaoRadioAutor1: Boolean? = null
    private var opcaoRadioAutor2: Boolean? = null
    private var respostaCertaCh: Boolean? = null
    private var respostaErradaCh: Boolean? = null
    private var segundaRespostaCertaCh: Boolean? = null
    private var opcaoRadioCantor1: Boolean? = null
    private var opcaoRadioCantor2: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        //Chamando o método no clique do botão
        btClassificar.setOnClickListener {
            enviarResposta()
        }
    }

        //Metodo que calcula a pontuação do participante, retorna um inteiro
       private fun calculcarPontuacao(
            musica: String, charlie: Boolean?, hermanos: Boolean?,
            respostaCertaCh: Boolean?, respostaErradaCh: Boolean?,
            segundaRespostaCertaCh: Boolean?, tom: Boolean?, caetano: Boolean?
        ): Int {

            val musicaCerta = "como e grande o meu amor por voce"

            if (hermanos == true && charlie == false) {
                pontos++ //pontos = pontos + 1;

            }
            if (musica.equals(musicaCerta, ignoreCase = true)) { //capslock será ignorado
                pontos++

            }
            if (respostaCertaCh == true && segundaRespostaCertaCh == true && respostaErradaCh == false) {
                pontos++

            }
            if (tom == true && caetano == false) {
                pontos++
            }

            return pontos
        }

    private fun enviarResposta() {

        participante = participanteEdit.text.toString()
        nomeMusica = respostaEdit.text.toString()

        //Validando os campos
        if((participante == null || participante.equals("")) || (nomeMusica == null || nomeMusica.equals(""))) {
            Toast.makeText(baseContext, "Preencha todos os campos corretamente",
                Toast.LENGTH_SHORT).show()
            finish()
        }else{
            opcaoRadioAutor1 = opcaoRadio1.isChecked
            opcaoRadioAutor2 = opcaoRadio2.isChecked

            respostaCertaCh = checkComplet.isChecked
            respostaErradaCh = checkComplet1.isChecked
            segundaRespostaCertaCh = checkComplet3.isChecked
            opcaoRadioCantor1 = opcaoRadioCantorPrimeira.isChecked
            opcaoRadioCantor2 = opcaoRadioCantorSegunda.isChecked

            //chamando o metodo com os parametros capturados
            calculcarPontuacao(
                nomeMusica!!,
                opcaoRadioAutor1,
                opcaoRadioAutor2,
                respostaCertaCh,
                respostaErradaCh,
                segundaRespostaCertaCh,
                opcaoRadioCantor1,
                opcaoRadioCantor2
            )

            //exibindo uma pequena mensagem rápida para o participante
            Toast.makeText(
                baseContext, "$participante, marcou $pontos pontos!",
                Toast.LENGTH_SHORT
            ).show()

            //pegando as informações para outra tela
            val data = Intent()
            data.putExtra(MainActivity.NOME_ACTIVITY_REQUEST, " Participante: $participante")
            data.putExtra(MainActivity.PONTUACAO_ACTIVITY_REQUEST, " Marcou: $pontos")
            setResult(Activity.RESULT_OK, data)
            finish()

            pontos = 0
        }

        }
    }


