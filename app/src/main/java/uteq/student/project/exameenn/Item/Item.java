package uteq.student.project.exameenn.Item;

import android.content.Context;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.squareup.picasso.Picasso;

import uteq.student.project.exameenn.modelos.modelo_journal;
import uteq.student.project.examenfinal.R;

@Layout(R.layout.lista_de_elementos)
public class Item {

    @View(R.id.name)
    private TextView name;

    @View(R.id.description)
    private TextView description;

    @View(R.id.portada)
    private ImageView portada;

    private uteq.student.project.exameenn.modelos.modelo_journal modelo_journal;
    private Context Context;

    public Item(Context context, modelo_journal modeloJournal) {
        Context = context;
        modelo_journal = modeloJournal;
    }

    @Resolve
    private void onResolved() {
        name.setText(modelo_journal.getName());
        description.setText(Html.fromHtml(modelo_journal.getDescription()));
        Picasso.get().load(modelo_journal.getPortada()).resize(100,100).centerCrop().into(portada);
    }
    @Click(R.id.card)
    public void onCardClick() {

    }

}
