package com.laila.unifacs.avaliacao1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.laila.unifacs.avaliacao1.R;

public class ConfigScreen extends AppCompatActivity {

    private RadioGroup coordGeograficasRadioGroup;
    private RadioGroup unidadeVelocidadeRadioGroup;
    private RadioGroup orientacaoMapaRadioGroup;
    private RadioGroup tipoMapaRadioGroup;
    private RadioGroup infoTrafegoRadioGroup;

    private RadioButton grauDecimalRadionButton, grauMinutoRadionButton, grauSegundoRadionButton;
    private RadioButton quilometroHoraRadionButton, milhaHoraRadionButton;
    private RadioButton nenhumaRadionButton, northUpRadionButton, courseUpRadionButton;
    private RadioButton vetorialRadionButton, sateliteRadionButton;
    private RadioButton ligadoRadionButton, desligadoRadionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        this.coordGeograficasRadioGroup =   findViewById(R.id.coordenadas_geograficas_RadioGroup);
        this.unidadeVelocidadeRadioGroup =  findViewById(R.id.unidade_velocidade_RadioGroup);
        this.orientacaoMapaRadioGroup =     findViewById(R.id.orientacao_mapa_RadioGroup);
        this.tipoMapaRadioGroup =           findViewById(R.id.tipo_mapa_RadioGroup);
        this.infoTrafegoRadioGroup =        findViewById(R.id.informacoes_trafego_RadioGroup);

        this.grauDecimalRadionButton =      findViewById(R.id.grau_decimal_RadioButton);
        this.grauMinutoRadionButton =       findViewById(R.id.grau_minuto_RadioButton);
        this.grauSegundoRadionButton =      findViewById(R.id.grau_minuto_segundo_RadioButton);

        this.quilometroHoraRadionButton =   findViewById(R.id.km_hora_RadioButton);
        this.milhaHoraRadionButton =        findViewById(R.id.milha_hora_RadioButton);

        this.nenhumaRadionButton =          findViewById(R.id.orientacao_nenhuma_RadioButton);
        this.northUpRadionButton =          findViewById(R.id.orientacao_north_up_RadioButton);
        this.courseUpRadionButton =         findViewById(R.id.orientacao_course_up_RadioButton);

        this.vetorialRadionButton =         findViewById(R.id.vetorial_RadioButton);
        this.sateliteRadionButton =         findViewById(R.id.imagem_satelite_RadioButton);

        this.ligadoRadionButton =           findViewById(R.id.trafego_ligado_RadioButton);
        this.desligadoRadionButton =        findViewById(R.id.trafego_desligado_RadioButton);

    }

    public void changeCoordGeografica(View view) {

    }
}