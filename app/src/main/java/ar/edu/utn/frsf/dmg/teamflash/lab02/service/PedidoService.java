package ar.edu.utn.frsf.dmg.teamflash.lab02.service;

import java.util.ArrayList;

import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.Pedido;

/**
 * Created by AdminUser on 02/09/2016.
 */
public interface PedidoService {

    interface MenuServiceCallBack {

        void onError(String msg);

        void onSuccess(Double montoTotal);
    }

    ArrayList<ElementoMenu> getListaBebidas();

    ArrayList<ElementoMenu> getListaPlatos();

    ArrayList<ElementoMenu> getListaPostres();

    void confirmarPedido(Pedido pedido, MenuServiceCallBack callBack);

}
