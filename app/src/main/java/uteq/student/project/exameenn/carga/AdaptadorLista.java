package uteq.student.project.exameenn.carga;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.mindorks.placeholderview.InfinitePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.infinite.LoadMore;

import java.util.ArrayList;

import uteq.student.project.exameenn.Item.Item;
import uteq.student.project.examenfinal.R;
import uteq.student.project.exameenn.modelos.modelo_journal;

@SuppressLint("NonConstantResourceId")
@Layout(R.layout.load)
public class AdaptadorLista {

    public static final int LOAD_VIEW_SET_COUNT = 3;

    private InfinitePlaceHolderView placeHolderView;
    private ArrayList<modelo_journal> modelo_journals;

    public AdaptadorLista(InfinitePlaceHolderView placeHolderView, ArrayList<modelo_journal> modelo_journals) {
        this.placeHolderView = placeHolderView;
        this.modelo_journals = modelo_journals;
    }

    @LoadMore
    private void onLoadMore(){
        Log.d("DEBUG", "onLoadMore");
        new ForcedWaitedLoading();
    }

    class ForcedWaitedLoading implements Runnable{

        public ForcedWaitedLoading() {
            new Thread(this).start();
        }

        @Override
        public void run() {

            try {
                Thread.currentThread().sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int count = placeHolderView.getViewCount();
                    for (int i = count - 1;
                         i < (count - 1 + AdaptadorLista.LOAD_VIEW_SET_COUNT) && modelo_journals.size() > i;
                         i++) {
                        placeHolderView.addView(new Item(placeHolderView.getContext(), modelo_journals.get(i)));

                        if(i == modelo_journals.size() - 1){
                            placeHolderView.noMoreToLoad();
                            break;
                        }
                    }
                    placeHolderView.loadingDone();
                }
            });
        }
    }
}