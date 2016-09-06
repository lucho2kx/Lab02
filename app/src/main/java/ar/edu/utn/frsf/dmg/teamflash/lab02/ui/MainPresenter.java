package ar.edu.utn.frsf.dmg.teamflash.lab02.ui;

import android.util.Log;

import java.util.ArrayList;

import ar.edu.utn.frsf.dmg.teamflash.lab02.R;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.Pedido;
import ar.edu.utn.frsf.dmg.teamflash.lab02.service.HorarioService;
import ar.edu.utn.frsf.dmg.teamflash.lab02.service.Impl.HorarioServiceImpl;
import ar.edu.utn.frsf.dmg.teamflash.lab02.service.Impl.PedidoServiceImpl;
import ar.edu.utn.frsf.dmg.teamflash.lab02.service.PedidoService;

/**
 * Created by AdminUser on 02/09/2016.
 */
public class MainPresenter implements IMainPresenter {

    private PedidoService pedidoService;
    private HorarioService horarioService;
    private IMainView view;
    private boolean confirmado= false;
    private ArrayList<ElementoMenu> listaPlatos= null;
    private ArrayList<ElementoMenu> listaPostres= null;
    private ArrayList<ElementoMenu> listaBebidas= null;

    public MainPresenter(IMainView view) {
        this.view = view;
        this.horarioService= new HorarioServiceImpl(this.view.getContext());
        this.pedidoService = new PedidoServiceImpl(this.view.getContext());
    }

    @Override
    public void onClickAgregar(ArrayList<ElementoMenu> elementoMenus) {
        if (confirmado) {
            view.showMensaje(view.getContext().getResources().getString(R.string.error_confirmacion));
        }
        else {
            if (elementoMenus.size() > 0) {
                view.showListaPedidos(elementoMenus);
            } else {
                view.clearListaPedidos();
                view.showMensaje(view.getContext().getResources().getString(R.string.error_lista_vacia));
            }
        }
    }

    @Override
    public void onClickConfirmar(Boolean reserva_delivery, String horario, Boolean notificar_reserva, ArrayList<ElementoMenu> elementoMenus) {
        if (confirmado) {
            view.showMensaje(view.getContext().getResources().getString(R.string.error_confirmacion));
        }
        else {
            if (elementoMenus.size() > 0) {
                // Se crea el pedido
                Pedido pedido= new Pedido();
                pedido.setReserva_delivery(reserva_delivery);
                pedido.setHorario(horario);
                pedido.setNotificarReserva(notificar_reserva);
                pedido.setLista(elementoMenus);
                Log.i("onClickConfirmar()","reserva_delivery="+reserva_delivery.toString());
                Log.i("onClickConfirmar()","horario="+horario.toString());
                Log.i("onClickConfirmar()","notificar_reserva="+notificar_reserva.toString());
                Log.i("onClickConfirmar()","elementoMenus.size()="+elementoMenus.size());

                // Se confirma el pedido
                pedidoService.confirmarPedido(pedido, new PedidoService.MenuServiceCallBack() {
                    @Override
                    public void onError(String msg) {
                        view.showMensaje(msg);
                    }

                    @Override
                    public void onSuccess(Double montoTotal) {
                        confirmado= true;
                        view.confirmedPedido(montoTotal);
                        view.showMensaje(view.getContext().getResources().getString(R.string.pedido_realizado));
                    }
                });
            }
            else {
                view.showMensaje(view.getContext().getResources().getString(R.string.error_lista_vacia));
            }
        }
    }

    @Override
    public void onClickReiniciar() {
        if (confirmado) {
            view.showMensaje(view.getContext().getResources().getString(R.string.error_confirmacion));
        }
        else {
            view.reiniciar();
        }
    }

    @Override
    public void showSpinnerHorarios() {
        ArrayList<String> lista= horarioService.listaHorarios();
        Log.i("showSpinnerHorarios()",""+lista.size());
        view.createListHorario(view.createListAdapterHorario(lista));
        view.showSpinnerHorarios();
    }

    @Override
    public void onClickRadioButton(int checkedId) {

        switch (checkedId) {
            case R.id.rdb_plato:
                if (listaPlatos == null) {
                    listaPlatos= pedidoService.getListaPlatos();
                }
                view.createListAdapter(listaPlatos);
                break;
            case R.id.rdb_postre:
                if (listaPostres == null) {
                    listaPostres= pedidoService.getListaPostres();
                }
                view.createListAdapter(listaPostres);
                break;
            case R.id.rdb_bebida:
                if (listaBebidas == null) {
                    listaBebidas= pedidoService.getListaBebidas();
                }
                view.createListAdapter(listaBebidas);
                break;
        }

        view.showListViewMenu();
    }

    @Override
    public void onItemClickUnCheck(ElementoMenu elementoMenu) {
        view.removeElementoMenu(elementoMenu);
    }

    @Override
    public void onItemClickCheck(ElementoMenu elementoMenu) {
        view.addElementoMenu(elementoMenu);
    }

}
