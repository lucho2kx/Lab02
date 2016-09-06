package ar.edu.utn.frsf.dmg.teamflash.lab02.model;

import java.util.ArrayList;

/**
 * Created by AdminUser on 06/09/2016.
 */
public class Pedido {

    private Boolean reserva_delivery;
    private String horario;
    private Boolean notificarReserva;
    private ArrayList<ElementoMenu> lista;

    public Pedido() {
    }

    public Boolean getReserva_delivery() {
        return reserva_delivery;
    }

    public void setReserva_delivery(Boolean reserva_delivery) {
        this.reserva_delivery = reserva_delivery;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Boolean getNotificarReserva() {
        return notificarReserva;
    }

    public void setNotificarReserva(Boolean notificarReserva) {
        this.notificarReserva = notificarReserva;
    }

    public ArrayList<ElementoMenu> getLista() {
        return lista;
    }

    public void setLista(ArrayList<ElementoMenu> lista) {
        this.lista = lista;
    }
}
