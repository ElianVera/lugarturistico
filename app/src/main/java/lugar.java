import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ArrayList<String> categorias;
    private ArrayList<String> nombres;
    private ArrayList<String> lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        categorias = new ArrayList<>();
        nombres = new ArrayList<>();
        lugares = new ArrayList<>();

        new ParseTask().execute("https://uealecpeterson.net/turismo/");
    }

    private class ParseTask extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                Document doc = Jsoup.connect(strings[0]).get();
                Elements elementos = doc.select("div[class=contenedor]");

                for (Element elemento : elementos) {
                    Elements titulos = elemento.select("h2");
                    Elements parrafos = elemento.select("p");

                    categorias.add(titulos.get(0).text());
                    nombres.add(parrafos.get(0).text());
                    lugares.add(parrafos.get(1).text());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < categorias.size(); i++) {
                stringBuilder.append("Categoría: ").append(categorias.get(i)).append("\n");
                stringBuilder.append("Nombre: ").append(nombres.get(i)).append("\n");
                stringBuilder.append("Lugar: ").append(lugares.get(i)).append("\n\n");
            }

            textView.setText(stringBuilder.toString());
        }
    }
}

