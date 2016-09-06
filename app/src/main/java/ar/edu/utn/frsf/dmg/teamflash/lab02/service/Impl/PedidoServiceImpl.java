package ar.edu.utn.frsf.dmg.teamflash.lab02.service.Impl;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import ar.edu.utn.frsf.dmg.teamflash.lab02.R;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.Pedido;
import ar.edu.utn.frsf.dmg.teamflash.lab02.service.PedidoService;

/**
 * Created by AdminUser on 02/09/2016.
 */
public class PedidoServiceImpl implements PedidoService {

    private Context context;

    public PedidoServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<ElementoMenu> getListaBebidas() {
            // inicia lista de bebidas
        ArrayList<ElementoMenu> listaBebidas = new ArrayList<>();

            String[] lista = context.getResources().getStringArray(R.array.bebidas);
            int tam = lista.length;
            Log.i("getListabebidas()", "tam=" + tam);
            for (int i = 1; i < tam; i++) {
                listaBebidas.add(new ElementoMenu(i + 1, lista[i]));
            }

        return listaBebidas;
    }

    @Override
    public ArrayList<ElementoMenu> getListaPlatos() {
            // inicia lista de platos
            ArrayList<ElementoMenu> listaPlatos = new ArrayList<>();

            String[] lista = context.getResources().getStringArray(R.array.platos);
            int tam = lista.length;
            Log.i("getListaPlatos()", "tam=" + tam);
            for (int i = 0; i < tam; i++) {
                listaPlatos.add(new ElementoMenu(i + 1, lista[i]));
            }
        return listaPlatos;
    }

    @Override
    public ArrayList<ElementoMenu> getListaPostres() {
            // inicia lista de postres
            ArrayList<ElementoMenu> listaPostres = new ArrayList<>();

            String[] lista = context.getResources().getStringArray(R.array.postres);
            int tam = lista.length;
            Log.i("getListaPostres()", "tam=" + tam);
            for (int i = 0; i < tam; i++) {
                listaPostres.add(new ElementoMenu(i + 1, lista[i]));
            }
        return listaPostres;
    }

    @Override
    public void confirmarPedido(Pedido pedido, MenuServiceCallBack callBack) {
        try {
            // Aquí debería persistir pedido en la Capa de acceso a datos,
            // también se deben validar los datos.
            // Vamos a dar por hecho que se realizó de manera correcta
            // dicha validación y persistencia
            Double res= 0.0;
            ArrayList<ElementoMenu> list= pedido.getLista();
            int tam= list.size();
            for (int i=0; i < tam; i++) {
                res= res + list.get(i).getPrecio();
            }
            callBack.onSuccess(res);
        }
        catch (Exception e) {
            // Error al guardar el pedido
            callBack.onError(e.getMessage().toString());
        }
    }
}
