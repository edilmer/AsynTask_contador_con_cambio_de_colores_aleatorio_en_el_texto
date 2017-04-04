package com.jonmid.asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    Button boton;
    ProgressBar loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.texto);
        boton = (Button) findViewById(R.id.boton);
        loader = (ProgressBar) findViewById(R.id.progressbar);
    }

    public void onButtonStartTask(View view){
        new MyAsyncTask().execute(60);
    }

    public class MyAsyncTask extends AsyncTask<Integer, Integer, String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loader.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            for(int i=0; i<=max; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                publishProgress(i);
            }
            return "Fin";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            int contador = values[0];
            String cadena = "Contador: "+contador;
            texto.setText(cadena);
            texto.setTextSize(contador);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            texto.append("\n"+s);
            loader.setVisibility(View.INVISIBLE);
        }
    }
}
