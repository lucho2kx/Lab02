package ar.edu.utn.frsf.dmg.teamflash.lab02.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.support.v7.widget.SwitchCompat;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import ar.edu.utn.frsf.dmg.teamflash.lab02.R;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.ElementoMenu;
import ar.edu.utn.frsf.dmg.teamflash.lab02.model.Pedido;
import ar.edu.utn.frsf.dmg.teamflash.lab02.ui.adapter.MenuAdapter;

public class MainActivity extends AppCompatActivity implements IMainView {

    private ArrayList<ElementoMenu> listaBebidas= new ArrayList<>();
    private ArrayList<ElementoMenu> listaPlatos= new ArrayList<>();
    private ArrayList<ElementoMenu> listaPostres= new ArrayList<>();

    private ToggleButton reserva_solicita_TButton;
    private Spinner selecHorario;
    private SwitchCompat notiReserva;
    private TextView listaPedidos;
    private RadioGroup radioGroupMenu;
    private Button agregar;
    private Button confirmar;
    private Button reiniciar;
    private ListView listaMenu;
    private ScrollView miScroll;

    private ArrayAdapter adapterHorarios;

    private MenuAdapter adapterMenu;

    private Boolean reserva_delivery= false;
    private String horario= "20:00";
    private Boolean notificar_reserva= false;
    private int checkedIdMenu=-1;

    private DecimalFormat f = new DecimalFormat("##.00");

    private IMainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reserva_solicita_TButton= (ToggleButton) findViewById(R.id.toggleButton);
        selecHorario= (Spinner) findViewById(R.id.spinner_horario);
        notiReserva= (SwitchCompat) findViewById(R.id.switch_notificar_reserva);
        listaPedidos= (TextView) findViewById(R.id.textView_lista_pedidos);
        radioGroupMenu = (RadioGroup) findViewById(R.id.rdb_menu);
        agregar= (Button) findViewById(R.id.button_agregar);
        confirmar= (Button) findViewById(R.id.button_confirmar);
        reiniciar= (Button) findViewById(R.id.button_reiniciar);
        listaMenu= (ListView) findViewById(R.id.listView_lista);
        miScroll= (ScrollView) findViewById(R.id.scrollView);

        reserva_solicita_TButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    reserva_delivery= isChecked;
                } else {
                    // The toggle is disabled
                    reserva_delivery= isChecked;
                }
            }
        });

        selecHorario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                horario= (String) adapterHorarios.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Hacer algo
            }

        });

        notiReserva.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                notificar_reserva= isChecked;
            }
        });

        radioGroupMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                checkedIdMenu= checkedId;
                presenter.onClickRadioButton(checkedId);
            }

        });

        miScroll.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                findViewById(R.id.listView_lista).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                findViewById(R.id.textView_lista_pedidos).getParent()
                        .requestDisallowInterceptTouchEvent(false);
                return false;
            }
        });

        listaPedidos.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        listaMenu.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hacer algo
                ArrayList<ElementoMenu> list= new ArrayList<ElementoMenu>();
                list.addAll(listaPlatos);
                list.addAll(listaPostres);
                list.addAll(listaBebidas);
                presenter.onClickAgregar(list);
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hacer algo
                ArrayList<ElementoMenu> list= new ArrayList<ElementoMenu>();
                list.addAll(listaPlatos);
                list.addAll(listaPostres);
                list.addAll(listaBebidas);
                presenter.onClickConfirmar( reserva_delivery,
                                            horario,
                                            notificar_reserva,
                                            list);
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hacer algo
                presenter.onClickReiniciar();
            }
        });

        presenter= new MainPresenter(this);

        presenter.showSpinnerHorarios();

    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }

    @Override
    public void createListHorario(ArrayAdapter adapter) {
        this.adapterHorarios = adapter;
        this.selecHorario.setAdapter(this.adapterHorarios);
    }

    @Override
    public ArrayAdapter createListAdapterHorario(ArrayList<String> horarios) {
        return new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, horarios);
    }

    @Override
    public void showSpinnerHorarios() {
        this.selecHorario.setVisibility(View.VISIBLE);
    }

    @Override
    public void showListViewMenu() {
        this.listaMenu.setVisibility(View.VISIBLE);
    }

    @Override
    public void createListAdapter(ArrayList<ElementoMenu> elementos) {

        switch (checkedIdMenu) {
            case R.id.rdb_plato:
                adapterMenu= new MenuAdapter(this, elementos, listaPlatos);
                break;
            case R.id.rdb_postre:
                adapterMenu= new MenuAdapter(this, elementos, listaPostres);
                break;
            case R.id.rdb_bebida:
                adapterMenu= new MenuAdapter(this, elementos, listaBebidas);
                break;
        }

        adapterMenu.setOnItemClickListener(new MenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClickUnCheck(ElementoMenu elementoMenu) {
                presenter.onItemClickUnCheck(elementoMenu);
            }

            @Override
            public void onItemClickCheck(ElementoMenu elementoMenu) {
                presenter.onItemClickCheck(elementoMenu);
            }
        });

        listaMenu.setAdapter(adapterMenu);
    }

    @Override
    public void addElementoMenu(ElementoMenu elementoMenu) {
        switch (checkedIdMenu) {
            case R.id.rdb_plato:
                listaPlatos.add(elementoMenu);
                break;
            case R.id.rdb_postre:
                listaPostres.add(elementoMenu);
                break;
            case R.id.rdb_bebida:
                listaBebidas.add(elementoMenu);
                break;
        }
    }

    @Override
    public void removeElementoMenu(ElementoMenu elementoMenu) {
        int tam=0;
        switch (checkedIdMenu) {
            case R.id.rdb_plato:
                tam= listaPlatos.size();
                for (int i=0; i < tam; i++) {
                    if (listaPlatos.get(i).getId() == elementoMenu.getId()) {
                        listaPlatos.remove(i);
                        break;
                    }
                }
                break;
            case R.id.rdb_postre:
                tam= listaPostres.size();
                for (int i=0; i < tam; i++) {
                    if (listaPostres.get(i).getId() == elementoMenu.getId()) {
                        listaPostres.remove(i);
                        break;
                    }
                }
                break;
            case R.id.rdb_bebida:
                tam= listaBebidas.size();
                for (int i=0; i < tam; i++) {
                    if (listaBebidas.get(i).getId() == elementoMenu.getId()) {
                        listaBebidas.remove(i);
                        break;
                    }
                }
                break;
        }
    }


    @Override
    public void showListaPedidos(ArrayList<ElementoMenu> listaElementos) {

        listaPedidos.setMovementMethod(new ScrollingMovementMethod());
        listaPedidos.setText(getResources().getString(R.string.datos_pedido));
        int tam= listaElementos.size();

        for (int i=0; i < tam; i++) {
            listaPedidos.setText(listaPedidos.getText().toString()+"\n"+listaElementos.get(i).toString());
        }


    }

    @Override
    public void showMensaje(String msj) {
        Toast.makeText(this, msj,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void confirmedPedido(Double montoTotal) {
        listaPedidos.setText(listaPedidos.getText().toString()+"\n"+getResources().getString(R.string.total)+" "+f.format(montoTotal));
    }

    @Override
    public void reiniciar() {
        listaPedidos.setText(getResources().getString(R.string.datos_pedido));
        listaBebidas= new ArrayList<>();
        listaPlatos= new ArrayList<>();
        listaPostres= new ArrayList<>();

        radioGroupMenu.clearCheck();

        listaMenu.setVisibility(View.INVISIBLE);
    }

    @Override
    public void clearListaPedidos() {
        listaPedidos.setText(getResources().getString(R.string.datos_pedido));
    }
}
