package ar.edu.utn.frsf.dmg.teamflash.lab02.ui;

import java.util.ArrayList;

import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;

/**
 * Created by AdminUser on 02/09/2016.
 */
public interface IMainPresenter {

    void onClickAgregar(ArrayList<ElementoMenu> elementoMenus);

    void onClickConfirmar(Boolean reserva_delivery,
                          String horario,
                          Boolean notificar_reserva,
                          ArrayList<ElementoMenu> elementoMenus);

    void onClickReiniciar();


    void onItemClickUnCheck(ElementoMenu elementoMenu);

    void onItemClickCheck(ElementoMenu elementoMenu);

    void onClickRadioButton(int checkedId);

    void showSpinnerHorarios();

}
