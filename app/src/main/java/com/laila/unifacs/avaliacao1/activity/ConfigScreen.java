package com.laila.unifacs.avaliacao1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.laila.unifacs.avaliacao1.R;

public class ConfigScreen extends AppCompatActivity {

    private SharedPreferences sharedPreferences;

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

    private int coordGeograficasRadionButtonSelected;
    private int unidadeVelocidadeRadioButtonSelected;
    private int orientacaoMapaRadioButtonSelected;
    private int tipoMapaRadioButtonSelected;
    private int infoTrafegoRadioButtonSelected;

    private final String PREFERENCE_NAME = "myPref";
    private final String COORD_GEO_KEY = "coordGeoKey";
    private final String UNIDADE_VELOCIDADE_KEY = "unidadeVelocidadeKey";
    private final String ORIENTACAO_MAPA_KEY = "orientacaoMapaKey";
    private final String TIPO_MAPA_KEY = "tipoMapaKey";
    private final String INFO_TRAFEGO_KEY = "infoTrafegoKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_screen);

        this.initViews();
        this.getPreferencesData();
    }

    public void initViews() {

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

    public void getPreferencesData() {

        this.sharedPreferences = getSharedPreferences(this.PREFERENCE_NAME, 0);

        this.coordGeograficasRadionButtonSelected = this.sharedPreferences.getInt(this.COORD_GEO_KEY, 0);
        this.unidadeVelocidadeRadioButtonSelected = this.sharedPreferences.getInt(this.UNIDADE_VELOCIDADE_KEY, 0);
        this.orientacaoMapaRadioButtonSelected = this.sharedPreferences.getInt(this.ORIENTACAO_MAPA_KEY, 0);
        this.tipoMapaRadioButtonSelected = this.sharedPreferences.getInt(this.TIPO_MAPA_KEY, 0);
        this.infoTrafegoRadioButtonSelected = this.sharedPreferences.getInt(this.INFO_TRAFEGO_KEY, 0);

        // Checks the button specified by id and unchecks the others in a RadioGroup
        this.coordGeograficasRadioGroup.check(this.coordGeograficasRadionButtonSelected);
        this.unidadeVelocidadeRadioGroup.check(this.unidadeVelocidadeRadioButtonSelected);
        this.orientacaoMapaRadioGroup.check(this.orientacaoMapaRadioButtonSelected);
        this.tipoMapaRadioGroup.check(this.tipoMapaRadioButtonSelected);
        this.infoTrafegoRadioGroup.check(this.infoTrafegoRadioButtonSelected);

    }

    public void savePreferences(View view) {

        // Get the id of the chosen RadioButton of each RadioGroup
        this.coordGeograficasRadionButtonSelected = this.coordGeograficasRadioGroup.getCheckedRadioButtonId();
        this.unidadeVelocidadeRadioButtonSelected = this.unidadeVelocidadeRadioGroup.getCheckedRadioButtonId();
        this.orientacaoMapaRadioButtonSelected = this.orientacaoMapaRadioGroup.getCheckedRadioButtonId();
        this.tipoMapaRadioButtonSelected = this.tipoMapaRadioGroup.getCheckedRadioButtonId();
        this.infoTrafegoRadioButtonSelected = this.infoTrafegoRadioGroup.getCheckedRadioButtonId();

        // Save data with SharedPreferences
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(COORD_GEO_KEY, coordGeograficasRadionButtonSelected);
        editor.putInt(UNIDADE_VELOCIDADE_KEY, unidadeVelocidadeRadioButtonSelected);
        editor.putInt(ORIENTACAO_MAPA_KEY, orientacaoMapaRadioButtonSelected);
        editor.putInt(TIPO_MAPA_KEY, tipoMapaRadioButtonSelected);
        editor.putInt(INFO_TRAFEGO_KEY, infoTrafegoRadioButtonSelected);
        editor.apply();

    }

    public void clearPreferences(View view) {

        this.coordGeograficasRadioGroup.clearCheck();
        this.unidadeVelocidadeRadioGroup.clearCheck();
        this.orientacaoMapaRadioGroup.clearCheck();
        this.tipoMapaRadioGroup.clearCheck();
        this.infoTrafegoRadioGroup.clearCheck();

    }
}