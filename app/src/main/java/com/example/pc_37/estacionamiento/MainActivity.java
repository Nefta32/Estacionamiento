package com.example.pc_37.estacionamiento;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Primer Programa
    /*TextView txtResultado;
    EditText edituno, editdos;
    Button calcula;
    Double a,b,c;*/

    //Segundo Programa
    TextView txtResul;
    EditText editHora, editFrac, editInpH, editInpM, editOutH, editOutM;
    Button buttonInp, buttonOut, buttonCalcula;
    private int HoraIn, MinutoIn, HoraOu, MinutoOu;
    Double H, F, H_I, M_I, H_O, M_O, Hora_Total, Min_Total, Tim_Tot_Pag, Pago_Total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResul=(TextView)findViewById(R.id.txtResul);
        editHora=(EditText)findViewById(R.id.editHora);
        editFrac=(EditText)findViewById(R.id.editFrac);
        editInpH=(EditText)findViewById(R.id.editInpH);
        editInpM=(EditText)findViewById(R.id.editInpM);
        editOutH=(EditText)findViewById(R.id.editOutH);
        editOutM=(EditText)findViewById(R.id.editOutM);
        buttonInp=(Button)findViewById(R.id.buttonInp);
        buttonOut=(Button)findViewById(R.id.buttonOut);
        buttonCalcula=(Button)findViewById(R.id.buttonCalcula);

        buttonInp.setOnClickListener(this);
        buttonOut.setOnClickListener(this);
        buttonCalcula.setOnClickListener(this);

        //Primer programa del dia
       /* txtResultado=(TextView)findViewById(R.id.txtResultado);
        edituno=(EditText)findViewById(R.id.edituno);
        editdos=(EditText)findViewById(R.id.editdos);
        calcula=(Button)findViewById(R.id.calcula);

        calcula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=Double.parseDouble(edituno.getText().toString());
                b=Double.parseDouble(editdos.getText().toString());
                c=a*b;
                txtResultado.setText("La multiplicacion es: "+c);
            }
        });*/


    }

    @Override
    public void onClick(View v) {
        if (v==buttonInp){
            final Calendar c = Calendar.getInstance();
            HoraIn=c.get(Calendar.HOUR_OF_DAY);
            MinutoIn=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    editInpH.setText(""+hourOfDay);
                    editInpM.setText(minute+"");
                }
            }, HoraIn, MinutoIn, false);
            timePickerDialog.show();
        }
        else if (v==buttonOut){
            final Calendar d = Calendar.getInstance();
            HoraOu=d.get(Calendar.HOUR_OF_DAY);
            MinutoOu=d.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay2, int minute2) {
                    editOutH.setText(""+hourOfDay2);
                    editOutM.setText(minute2+"");
                }
            }, HoraOu, MinutoOu, false);
            timePickerDialog.show();
        }
        else if (v==buttonCalcula){

                //Recuperación de datos tomados de los EditText
            H_I=Double.parseDouble(editInpH.getText().toString());
            M_I=Double.parseDouble(editInpM.getText().toString());
            H_O=Double.parseDouble(editOutH.getText().toString());
            M_O=Double.parseDouble(editOutM.getText().toString());

            Double Min_Act2 = 60.00; //Variable de ayuda en caso de que el Min_Act1 sea negativo.

            Double Min_Act1 = M_O - M_I; //Inicio para conocer los minutos transcurridos.

            if (Min_Act1 < 0){   //Pregunta si Min_Act1 es negativo.
                H_O = H_O-1;    //Si lo es, resta una hora a la hora actual.
                Min_Act1 = Min_Act1*(-1);   //Multiplicamos por -1 para hacerlo positivo.
                Min_Total = Min_Act2-Min_Act1; //Asignamos los minutos de diferencia.
            }
            else if (Min_Act1 >= 0){    //De lo contrario.
                Min_Total = Min_Act1; //Asignamos los minutos de diferencia
            }

            if (H_O < H_I){  //Preguntamos si la hora de salida es menor a la hora de entrada.
                H_O = H_O+24;   //Si lo es, le suma 24 horas.
            }

            Hora_Total = H_O - H_I; //Asignamos las horas de diferencia

                //Recuperación de los datos de EditText
            H=Double.parseDouble(editHora.getText().toString());
            F=Double.parseDouble(editFrac.getText().toString());

            if (Hora_Total <= 1){
                Tim_Tot_Pag = Min_Total/15;
                Tim_Tot_Pag = Tim_Tot_Pag*F;
                Pago_Total = Tim_Tot_Pag+H;
                txtResul.setText("El pago es: $"+Math.round(Pago_Total));
            }
            else if (Hora_Total > 1){
                Hora_Total = Hora_Total - 1;
                Hora_Total = Hora_Total * 60;
                Tim_Tot_Pag = Hora_Total + Min_Total;
                Tim_Tot_Pag = Tim_Tot_Pag / 15;
                Pago_Total = (Tim_Tot_Pag * F) + H;
                txtResul.setText("El pago es: $"+Math.round(Pago_Total));
            }
        }
    }
}
