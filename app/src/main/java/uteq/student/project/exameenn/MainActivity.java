package uteq.student.project.exameenn;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.mindorks.placeholderview.InfinitePlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import uteq.student.project.exameenn.carga.AdaptadorLista;
import uteq.student.project.exameenn.modelos.modelo_journal;
import uteq.student.project.exameenn.Item.Item;
import uteq.student.project.examenfinal.R;

public class MainActivity extends AppCompatActivity {

    private modelo_journal modelojournal;
    ArrayList<modelo_journal> datos= new ArrayList<>();
    private InfinitePlaceHolderView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.sdasdsad);
        obtenerDatosConVolley();
    }

    private void setupView(ArrayList<modelo_journal> feedList){
        for(int i = 0; i < AdaptadorLista.LOAD_VIEW_SET_COUNT; i++){
            view.addView(new Item(this.getApplicationContext(), feedList.get(i)));
        }
    }
    private void obtenerDatosConVolley()
    {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0) {
                            datos.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject Obj = response.getJSONObject(i);
                                    datos.add(new modelo_journal(
                                            Obj.getString("journal_id"),
                                            Obj.getString("portada"),
                                            Obj.getString("abbreviation"),
                                            Obj.getString("description"),
                                            Obj.getString("name")));

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            setupView(datos);
                        }else
                        {
                            mensajes("No se encontraron resultados");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        mensajes("Ocurrio un error");
                        error.printStackTrace();

                    }
                });
        queue.add(jsonObjectRequest);
    }
    public void  mensajes(String mensaje)
    {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG ).show();
    }
}