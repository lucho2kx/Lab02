package ar.edu.utn.frsf.dmg.teamflash.lab02.ui.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ar.edu.utn.frsf.dmg.teamflash.lab02.R;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;

/**
 * Created by AdminUser on 02/09/2016.
 */
public class MenuAdapter extends BaseAdapter {

    private OnItemClickListener onItemClickListener;
    private Context context;
    private ArrayList<ElementoMenu> elementos;
    private SparseBooleanArray seleccionados;
    private int tam= 0;

    public MenuAdapter(Context context, ArrayList<ElementoMenu> elementos, ArrayList<ElementoMenu> elementosSelecionados) {
        this.context= context;
        this.elementos = elementos;
        this.tam= elementos.size();
        this.seleccionados= new SparseBooleanArray();
        this.setSeleccionados(elementosSelecionados);
    }

    private void setSeleccionados(ArrayList<ElementoMenu> elementosSeleccionados) {
        int t= elementosSeleccionados.size();
        for (int j=0; j < t; j++ ) {
            for (int i=0; i < tam; i++) {
                if (elementos.get(i).getId() == elementosSeleccionados.get(j).getId()) {
                    seleccionados.put(i,true);
                    break;
                }
            }
        }
    }

    @Override
    public int getCount() {
        return tam;
    }

    @Override
    public Object getItem(int i) {
        return elementos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final int position= i;
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView= inflater.inflate(R.layout.list_item_menu, null);

            viewHolder.layout= (RelativeLayout) convertView.findViewById(R.id.relativeLayout);
            viewHolder.nombre= (TextView) convertView.findViewById(R.id.textView_elemento_descripcion);
            viewHolder.rdb_seleccionado= (RadioButton) convertView.findViewById(R.id.radioButton_elemento);
            //Selecciona el objeto si estaba seleccionado
            if (seleccionados.get(position)) {
                viewHolder.layout.setSelected(true);
                viewHolder.rdb_seleccionado.setChecked(true);
}
            else {
                viewHolder.layout.setSelected(false);
                viewHolder.rdb_seleccionado.setChecked(false);
            }
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.nombre.setText(elementos.get(position).toString());
        viewHolder.rdb_seleccionado.setChecked(seleccionados.get(position));

        viewHolder.rdb_seleccionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rdb= (RadioButton) v;
                boolean b= !seleccionados.get(position);
                rdb.setChecked(!b);
                seleccionados.put(position, b);
                if (b)
                    onItemClickListener.onItemClickCheck(elementos.get(position));
                else
                    onItemClickListener.onItemClickUnCheck(elementos.get(position));

                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    // Establece el listener a informar cuando se hace click sobre un ítem.
    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    // Interfaz que debe implementar el listener para cuando se haga click sobre un elemento.
    public interface OnItemClickListener {

        void onItemClickUnCheck(ElementoMenu elementoMenu);

        void onItemClickCheck(ElementoMenu elementoMenu);

    }

    static class ViewHolder {
        protected RelativeLayout layout;
        protected TextView nombre;
        protected RadioButton rdb_seleccionado;

   }
}
