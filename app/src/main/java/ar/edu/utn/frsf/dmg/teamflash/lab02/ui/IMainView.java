package ar.edu.utn.frsf.dmg.teamflash.lab02.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;
import ar.edu.utn.frsf.dmg.teamflash.lab02.ui.adapter.MenuAdapter;

/**
 * Created by AdminUser on 02/09/2016.
 */
public interface IMainView {

    Context getContext();

    void showMensaje(String msj);

    void showSpinnerHorarios();

    void showListViewMenu();

    void createListAdapter(ArrayList<ElementoMenu> elementos);

    void createListHorario(ArrayAdapter adapter);

    ArrayAdapter createListAdapterHorario(ArrayList<String> horarios);

    void addElementoMenu(ElementoMenu elementoMenu);

    void removeElementoMenu(ElementoMenu elementoMenu);

    void showListaPedidos(ArrayList<ElementoMenu> listaElementos);

    void confirmedPedido(Double montoTotal);

    void reiniciar();

    void clearListaPedidos();

}
